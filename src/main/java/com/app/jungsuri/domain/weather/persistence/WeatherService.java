package com.app.jungsuri.domain.weather.persistence;

import com.app.jungsuri.domain.weather.domain.*;
import com.app.jungsuri.domain.weather.web.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class WeatherService {
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = System.getenv("weatherAPIKey"); // 발급받은 API key//

    public WeatherEntity getWeatherData(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "="+cityName); //+cityName
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            WeatherTotalDto weatherTotalDto = restTemplate.getForObject(urlBuilder.toString(), WeatherTotalDto.class);
            WeatherEntity weatherEntity = mapToWeatherData(weatherTotalDto);

            return weatherEntity;
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    public WeatherEntity mapToWeatherData(WeatherTotalDto weatherTotalDto) {
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
        ZoneId zoneId = ZoneId.systemDefault();

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
