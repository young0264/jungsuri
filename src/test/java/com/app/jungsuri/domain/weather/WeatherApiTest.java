package com.app.jungsuri.domain.weather;

import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.infra.MockMvcTest;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@MockMvcTest
public class WeatherApiTest {

    @Test
    @WithMockUser(username = "12", password = "12")
    public void test() throws IOException, ParseException {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = "발급받은 API key"; // 발급받은 API key
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
            //WeatherEntity로 출력 시작
            //seongnam: 1897000, koesan: 1842800, seoul: 1835848
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=seoul");
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            RestTemplate restTemplate = new RestTemplate();
            WeatherEntity response = restTemplate.getForObject(urlBuilder.toString(), WeatherEntity.class);
            System.out.println(response.toString());
            //WeatherEntity로 출력 끝

            //JSON으로 출력 시작
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            String result= sb.toString();
            System.out.println(result);
            //JSON으로 출력 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
