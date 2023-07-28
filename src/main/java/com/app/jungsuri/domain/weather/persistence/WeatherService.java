package com.app.jungsuri.domain.weather.persistence;

import com.app.jungsuri.domain.weather.domain.Sys;
import com.app.jungsuri.domain.weather.domain.Weather;
import com.app.jungsuri.domain.weather.domain.WeatherImage;
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
    private final String apiKey = "발급받은 API key"; // 발급받은 API key//

    public WeatherEntity getWeatherData(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "="+cityName); //+cityName
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            WeatherEntity weatherEntity = restTemplate.getForObject(urlBuilder.toString(), WeatherEntity.class);

            /** 데이터 변환 시작 **/
            setWeatherImgSrc(weatherEntity);
            setSunTime(weatherEntity);
            /** 데이터 변환 끝 **/

            log.info("weatherEntity : " + weatherEntity.toString());
            return weatherEntity;
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    private void setWeatherImgSrc(WeatherEntity weatherEntity) {
        Weather weather = weatherEntity.getWeather().get(0);
        weather.setImgSrc(WeatherImage.getImgSrc(weather.getMain()));
    }

    private void setSunTime(WeatherEntity weatherEntity) {
        Sys sys = weatherEntity.getSys();
        sys.setSunriseTime(getWeatherSunTime(sys.getSunrise()));
        sys.setSunsetTime(getWeatherSunTime(sys.getSunset()));
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
}
