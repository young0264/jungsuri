package com.app.jungsuri.domain.weather.persistence;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@Transactional
public class WeatherService {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = System.getenv("weatherAPIKey"); // 발급받은 API key


    public WeatherEntity getWeatherData(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            //seongnam: 1897000, koesan: 1842800, seoul: 1835848
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=seoul"); //+cityName
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            WeatherEntity response = restTemplate.getForObject(urlBuilder.toString(), WeatherEntity.class);
//            System.out.println(response.toString());
            return response;

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
