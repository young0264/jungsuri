package com.app.jungsuri.domain.weather.persistence;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity getWeather(RequestParam city) {
        log.info("city 123 : ");
        log.info(city.name());
        WeatherEntity weatherData = weatherService.getWeatherData(city.name());
        return ResponseEntity.ok(weatherData);
    }
}
