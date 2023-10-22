package com.app.jungsuri.domain.weather;

import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.domain.weather.persistence.WeatherService;
import com.app.jungsuri.domain.weather.web.dto.WeatherTotalDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@MockMvcTest
public class WeatherApiTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    @WithMockUser(username = "12", password = "12")
    public void weatherServiceTest() throws IOException {
        weatherService.getWeatherData("seoul");
        Assertions.assertThat(weatherService.getWeatherData("seoul")).isNotNull();
        Assertions.assertThat(weatherService.getWeatherData("seoul").getCity().getName()).isEqualTo("Seoul");
    }


    @Test
    @WithMockUser(username = "12", password = "12")
    public void 기상청API_잘받아와지는지() throws IOException {
        weatherService.getWeatherDataByKMA();
    }

    @Test
    public void openweatherAPI_일출일몰시간() throws IOException {
        weatherService.getWeatherData("seoul");
    }

    @Test
    public void 년월일_8자리() {
        String weatherDataToStr = weatherService.getWeatherDataToStr();
        Assertions.assertThat(weatherDataToStr.length()).isEqualTo(8);
    }

    @Test
    @WithMockUser(username = "12", password = "12")
    public void openWeatherAPI_잘받아와지는지() throws IOException, ParseException {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = System.getenv("weatherAPIKey"); // 발급받은 API key
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            //WeatherEntity로 출력 시작
            urlBuilder.append("?").append(URLEncoder.encode("q", StandardCharsets.UTF_8)).append("=seoul");
            urlBuilder.append("&").append(URLEncoder.encode("appid", StandardCharsets.UTF_8)).append("=").append(apiKey);
            urlBuilder.append("&").append(URLEncoder.encode("lang", StandardCharsets.UTF_8)).append("=kr");
            urlBuilder.append("&").append(URLEncoder.encode("units", StandardCharsets.UTF_8)).append("=metric");

            RestTemplate restTemplate = new RestTemplate();
            WeatherTotalDto weatherTotalDto = restTemplate.getForObject(urlBuilder.toString(), WeatherTotalDto.class);

            WeatherEntity weatherEntity = weatherService.OpenWeathermapToData(weatherTotalDto);
            System.out.println(weatherEntity.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
