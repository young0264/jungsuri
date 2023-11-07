package com.app.jungsuri.domain.weather.service;

import com.app.jungsuri.domain.weather.dto.*;
import com.app.jungsuri.domain.weather.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Service
@Transactional
public class WeatherService {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String apiKey = System.getenv("weatherAPIKey"); // 발급받은 API key//

    /**
     * openweather api 혹은 기상청 api를 이용하여 날씨 정보를 받아와서
     * WeatherEntity로 반환하는 메소드
     * */
    public WeatherEntity getWeatherData(String cityName) throws IOException {
        WeatherEntity weatherDataByOpenWeatherAPI = getWeatherDataByOpenWeatherAPI(cityName);
        if (weatherDataByOpenWeatherAPI != null) {
            return weatherDataByOpenWeatherAPI;
        } return getWeatherDataByKMA();
    }

    /** open weather api를 이용하여 날씨 정보를 받아오는 클래스 */
    private WeatherEntity getWeatherDataByOpenWeatherAPI(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "="+cityName); //+cityName
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            WeatherTotalDto weatherTotalDto = restTemplate.getForObject(urlBuilder.toString(), WeatherTotalDto.class);
            WeatherEntity weatherEntity = OpenWeathermapToData(weatherTotalDto);
            return weatherEntity;
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 한국 기상청에서 받아오는 단기예보 데이터
     * fcstDate : 금일 + 2 = 총 3일치 데이터를 가져옴
     * fcstTime : 시간에 해당하는 예측 데이터
     * open weather api가 응답이 없으면 getWeatherDataByKMA()를 호출하도록 함
     * 그리고 해당 날짜(당일) 해당시간(예측시간)에 해당하는 데이터를 가져옴
     * TODO 이에 대한 로직을 weatherTotalDto 혹은 다른 Dto를 만들어서 WeatherEntity를 리턴하게 만들면 됨
     *
     * 도시별이 안되니까.
     * 오픈 웨더가 죽으면 -> 현재 날씨만 나오게
     **/
    public WeatherEntity getWeatherDataByKMA() throws IOException {
        String baseTime = getBaseTime();
        String fcstTime = getFcstTime();
        String weatherDate = getWeatherDataToStr();
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* 단기예보 */
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /* 초단기예보 */

        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=mWBoJtXClfKT%2BEP7C3NxnBIiM3es70NPDYGAN8Lov3Pjrzx8bqq3voaLIZ9cmpia8Yo8DnvfUgUM0rzGke8CgQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(weatherDate, "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        /** StringBuilder에 json 형식의 문자형 데이터 넣기 */
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        /** 결과 출력 시작 */
        ObjectMapper objMapper = new ObjectMapper();

        String jsonString = sb.toString(); // 문자열을 저장하는 StringBuilder 또는 StringBuffer입니다.
        JsonNode rootNode = objMapper.readTree(jsonString);

        /** 내가 찾는 time과 같은 데이터 가져오기 */
        JSONObject jsonObject = new JSONObject();
        for (JsonNode item : rootNode.get("response").get("body").get("items").get("item")) {
            if (item.get("fcstTime").toString().replaceAll("\"", "").equals(fcstTime)) {
                jsonObject.put(item.get("category").toString().replaceAll("\"", ""), item.get("fcstValue").toString().replaceAll("\"", ""));
            }
        }

        KoreaWeatherDto koreaWeatherDto = initKoreaWeatherData(jsonObject);
        WeatherEntity weatherEntity = KRWeathermapToData(koreaWeatherDto);

        return weatherEntity;
    }

    /**
     * 현재 시간과 가까분 00분 단위의 시간, 분
     * 단기 예측정보이기 때문에 baseTime + 5:30 시간에 대한 예측 정보가 주어짐
     * */
    private String getFcstTime() {
        String fcstTime = "";
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();

        /** 시간 초기화 */
        if (minute < 30) {
            hour -= 1;
            if (hour < 0) {
                hour = 23;
            }
        }
        /** 시간 2자리로 변환 */
        if (hour+1 < 10) {
            fcstTime = "0" + hour + "00";
        } else {
            fcstTime = hour+1 + "00";
        }
        return fcstTime;

    }

    /** 현재 시간과 가까운 30분 단위의 시간, 분 */
    private String getBaseTime() {
        String baseTime = "";
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        /** 시간 초기화 */
        if (minute < 30) {
            hour -= 1;
            if (hour < 0) {
                hour = 23;
            }
        }
        /** 시간 2자리로 변환 */
        if (hour < 10) {
            baseTime = "0" + hour + "30";
        } else {
            baseTime = hour + "30";
        }
        return baseTime;
    }


    /** 8자리의 문자타입의 년월일 반환 */
    public String getWeatherDataToStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }


    /** 기상청 weather api 데이터 초기화 시작 */
    private KoreaWeatherDto initKoreaWeatherData(JSONObject jsonObject) {
        KoreaWeatherDto koreaWeatherDto = new KoreaWeatherDto();
        koreaWeatherDto.setSKY(jsonObject.get("SKY").toString().replaceAll("\"", ""));
        koreaWeatherDto.setPTY(jsonObject.get("PTY").toString().replaceAll("\"", ""));
        koreaWeatherDto.setRN1(jsonObject.get("RN1").toString().replaceAll("\"", ""));
        koreaWeatherDto.setREH(jsonObject.get("REH").toString().replaceAll("\"", ""));
        koreaWeatherDto.setUUU(jsonObject.get("UUU").toString().replaceAll("\"", ""));
        koreaWeatherDto.setVEC(jsonObject.get("VEC").toString().replaceAll("\"", ""));
        koreaWeatherDto.setVVV(jsonObject.get("VVV").toString().replaceAll("\"", ""));
        koreaWeatherDto.setLGT(jsonObject.get("LGT").toString().replaceAll("\"", ""));
        koreaWeatherDto.setWSD(jsonObject.get("WSD").toString().replaceAll("\"", ""));
        koreaWeatherDto.setT1H(jsonObject.get("T1H").toString().replaceAll("\"", ""));
        return koreaWeatherDto;
    }

    public WeatherEntity KRWeathermapToData(KoreaWeatherDto koreaWeatherDto) {

        return WeatherEntity.builder()
                .weather(initKRWeather(koreaWeatherDto))
                .sunTimeInfo(initKRSunTimeInfo(koreaWeatherDto))
                .temperature(initKRTemperature(koreaWeatherDto))
                .atmospheric(initKRAtmospheric(koreaWeatherDto))
                .wind(initKRWind(koreaWeatherDto))
                .clouds(initKRClouds(koreaWeatherDto))
                .rain(initKRRain(koreaWeatherDto))
                .snow(initKRSnow(koreaWeatherDto))
                .city(initKRCity(koreaWeatherDto))
                .weatherConfig(initKRConfig(koreaWeatherDto))
                .build();
    }

    private String getWeatherInfo(KoreaWeatherDto koreaWeatherDto) {
        if (koreaWeatherDto.getSKY().equals("1")) {
            return "CLEAR";
        } else if (koreaWeatherDto.getSKY().equals("2")
                || koreaWeatherDto.getSKY().equals("3")
                || koreaWeatherDto.getSKY().equals("4")) {
            return "CLOUDS";
        } else if (koreaWeatherDto.getPTY().equals("1")
            || koreaWeatherDto.getPTY().equals("4")) {
            return "RAIN";
        } else if(koreaWeatherDto.getPTY().equals("2")
            || koreaWeatherDto.getPTY().equals("3")) {
            return "SNOW";
        } else if(! koreaWeatherDto.getLGT().equals("0")) {
            return "THUNDERSTORM";
        } return "DEFAULT";
    }

    private Weather initKRWeather(KoreaWeatherDto koreaWeatherDto) {
        return Weather.builder()
                .statusEn(toSkyStrCode(koreaWeatherDto.getSKY()))
                .statusKo(toSkyStrCode(koreaWeatherDto.getSKY()))
                .imgSrc(WeatherImageDto.getImgSrc(getWeatherInfo(koreaWeatherDto)))
                .build();

//        getWeatherInfo(koreaWeatherDto)
//                        .imgSrc(WeatherImageDto.getImgSrc(weatherinfo.getMain()))

    }

    private String toSkyStrCode(String weatherStatusCode) {
        switch (weatherStatusCode) {
            case "1":
                return "맑음";
            case "2":
                return "구름조금";
            case "3":
                return "구름많음";
            case "4":
                return "흐림";
            default:
                return "맑음";
        }
    }
    private SunTimeInfo initKRSunTimeInfo(KoreaWeatherDto koreaWeatherDto) {
        return SunTimeInfo.builder()
                .build();
    }

    private Temperature initKRTemperature(KoreaWeatherDto koreaWeatherDto) {
        return Temperature.builder()
                .temp(Float.parseFloat(koreaWeatherDto.getT1H().replaceAll("\"", "")))
                .build();
    }

    private Atmospheric initKRAtmospheric(KoreaWeatherDto koreaWeatherDto) {
        return Atmospheric.builder()
                .humidity(Integer.parseInt(koreaWeatherDto.getREH()))
                .build();
    }

    private Wind initKRWind(KoreaWeatherDto koreaWeatherDto) {
        return Wind.builder()
                .speed(Float.parseFloat(koreaWeatherDto.getWSD()))
                .build();
    }

    private Clouds initKRClouds(KoreaWeatherDto koreaWeatherDto) {
        return Clouds.builder()
//                .cloudiness(Integer.parseInt(koreaWeatherDto.getSKY()))
                .build();
    }

    private Rain initKRRain(KoreaWeatherDto koreaWeatherDto) {
        return Rain.builder()
//                .rainPast1h(Float.parseFloat(koreaWeatherDto.getRN1()))
                .build();
    }

    private Snow initKRSnow(KoreaWeatherDto koreaWeatherDto) {
        return Snow.builder()
                .build();
    }


    private City initKRCity(KoreaWeatherDto koreaWeatherDto) {
        return City.builder()
                .build();
    }

    private WeatherConfig initKRConfig(KoreaWeatherDto koreaWeatherDto) {
        return WeatherConfig.builder()
                .build();
    }

    /** 기상청 weather api 데이터 초기화 끝*/


    /** OpenWeather api 데이터 초기화 시작 */
    public WeatherEntity OpenWeathermapToData(WeatherTotalDto weatherTotalDto) {
        return WeatherEntity.builder()
                .weather(initWeather(weatherTotalDto))
                .sunTimeInfo(initSunTimeInfo(weatherTotalDto))
                .temperature(initTemperature(weatherTotalDto))
                .atmospheric(initAtmospheric(weatherTotalDto))
                .wind(initWind(weatherTotalDto))
                .clouds(initClouds(weatherTotalDto))
                .rain(initRain(weatherTotalDto))
                .snow(initSnow(weatherTotalDto))
                .city(initCity(weatherTotalDto))
                .weatherConfig(initConfig(weatherTotalDto))
                .build();
    }

    private WeatherConfig initConfig(WeatherTotalDto weatherTotalDto) {
        return WeatherConfig.builder()
                .base(weatherTotalDto.getBase())
                .cod(weatherTotalDto.getCod())
                .timezone(weatherTotalDto.getTimezone())
                .dt(weatherTotalDto.getDt())
                .build();
    }

    private String getWeatherSunTime(Long unixTimestamp) {

        // Instant 객체로 변환
        Instant instant = Instant.ofEpochSecond(unixTimestamp);

        // 시간대 설정 (예: 한국의 경우 "Asia/Seoul" 등)
        ZoneId zoneId = ZoneId.of("Asia/Seoul");

        // Instant를 LocalDateTime으로 변환
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 시간 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h:mm");

        // 오전 또는 오후를 포함한 시간 출력
        return localDateTime.format(formatter);
    }


    private City initCity(WeatherTotalDto weatherTotalDto) {
        return City.builder()
                .id(weatherTotalDto.getId())
                .name(weatherTotalDto.getName())
                .build();
    }

    private Snow initSnow(WeatherTotalDto weatherTotalDto) {
        SnowDto snowDto = weatherTotalDto.getSnow();
        if(snowDto == null) return null;
        return Snow.builder()
                .snowPast1h(snowDto.getSnow1h())
                .snowPast3h(snowDto.getSnow3h())
                .build();
    }

    private Rain initRain(WeatherTotalDto weatherTotalDto) {
        RainDto rainDto = weatherTotalDto.getRain();
        if (rainDto == null) {
            return null;
        }
        return Rain.builder()
                .rainPast1h(rainDto.getRain1h())
                .rainPast3h(rainDto.getRain3h())
                .build();
    }

    private Clouds initClouds(WeatherTotalDto weatherTotalDto) {
        CloudsDto clouds = weatherTotalDto.getClouds();
        if(clouds == null) return null;
        return Clouds.builder()
                .cloudiness(clouds.getAll())
                .speed(clouds.getSpeed())
                .build();
    }

    private Wind initWind(WeatherTotalDto weatherTotalDto) {
        WindDto windDto = weatherTotalDto.getWind();
        if(windDto == null) return null;
        return Wind.builder()
                .speed(windDto.getSpeed())
                .direction(windDto.getDeg())
                .gust(windDto.getGust())
                .build();
    }

    private Atmospheric initAtmospheric(WeatherTotalDto weatherTotalDto) {
        MainDto mainDto = weatherTotalDto.getMain();
        if(mainDto == null) return null;
        return Atmospheric.builder()
                .pressure(mainDto.getPressure())
                .humidity(mainDto.getHumidity())
                .seaLevel(mainDto.getSea_level())
                .groundLevel(mainDto.getGrnd_level())
                .visibility(weatherTotalDto.getVisibility())
                .build();
    }

    private Temperature initTemperature(WeatherTotalDto weatherTotalDto) {
        MainDto mainDto = weatherTotalDto.getMain();
        if(mainDto == null) return null;
        return Temperature.builder()
                .temp(mainDto.getTemp())
                .feelsLike(mainDto.getFeels_like())
                .tempMin(mainDto.getTemp_min())
                .tempMax(mainDto.getTemp_max())
                .build();
    }

    private SunTimeInfo initSunTimeInfo(WeatherTotalDto weatherTotalDto) {
        SysDto sysDto = weatherTotalDto.getSys();
        if(sysDto == null) return null;
        log.info("sysDto : " + sysDto.toString());
        return SunTimeInfo.builder()
                .type(sysDto.getType())
                .id(sysDto.getId())
                .sunrise(sysDto.getSunrise())
                .sunset(sysDto.getSunset())
                .sunriseTime(getWeatherSunTime(sysDto.getSunrise()))
                .sunsetTime(getWeatherSunTime(sysDto.getSunset()))
                .build();
    }

    private Weather initWeather(WeatherTotalDto weatherTotalDto) {
        WeatherDto weatherinfo = weatherTotalDto.getWeather().get(0);
        if(weatherinfo == null) return null;
        return Weather.builder()
                .id(weatherinfo.getId())
                .statusEn(weatherinfo.getMain())
                .statusKo(weatherinfo.getDescription())
                .iconId(weatherinfo.getIcon())
                .imgSrc(WeatherImageDto.getImgSrc(weatherinfo.getMain()))
                .build();
    }
}
