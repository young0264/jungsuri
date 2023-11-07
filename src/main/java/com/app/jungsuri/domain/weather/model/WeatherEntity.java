package com.app.jungsuri.domain.weather.model;

import com.app.jungsuri.domain.weather.model.*;
import lombok.*;

/**
 * OpenWeath API - 현재 날씨 데이터
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {

    private Weather weather;

    private SunTimeInfo sunTimeInfo;

    private Temperature temperature;

    private Atmospheric atmospheric;

    private Wind wind;

    private Clouds clouds;

    private Rain rain;

    private Snow snow;

    private City city;

    /** 사용자에게 필요하지 않은 내부 매개(메타) 정보들 */
    private WeatherConfig weatherConfig;

}
