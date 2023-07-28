package com.app.jungsuri.domain.weather;

import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.domain.weather.persistence.WeatherService;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URLEncoder;

@MockMvcTest
public class WeatherApiTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    @WithMockUser(username = "12", password = "12")
    public void weatherServiceTest()  {
        weatherService.getWeatherData("seoul");
        Assertions.assertThat(weatherService.getWeatherData("seoul")).isNotNull();
        Assertions.assertThat(weatherService.getWeatherData("seoul").getName()).isEqualTo("Seoul");
    }

    @Test
    @WithMockUser(username = "12", password = "12")
    public void openWeatherAPI_잘받아와지는지() throws IOException, ParseException {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = "발급받은 API key"; // 발급받은 API key
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            //WeatherEntity로 출력 시작
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=seoul");
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            RestTemplate restTemplate = new RestTemplate();
            WeatherEntity response = restTemplate.getForObject(urlBuilder.toString(), WeatherEntity.class);
            System.out.println(response.toString());
            //WeatherEntity로 출력 끝

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
