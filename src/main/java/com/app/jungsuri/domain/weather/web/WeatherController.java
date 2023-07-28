package com.app.jungsuri.domain.weather.persistence;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping("/weather")
    public ResponseEntity getWeather(@RequestParam("city") String city) {
        log.info("city 123 : ");
        log.info(city);
        WeatherEntity weatherData = weatherService.getWeatherData(city);
        return ResponseEntity.ok(weatherData);
    }
}
