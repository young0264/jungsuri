package com.app.jungsuri.domain.weather.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Snow {

    /** 지난 1 시간 동안의 눈량, mm */
    private float snowPast1h;

    /** 지난 3 시간 동안의 눈량, mm */
    private float snowPast3h;
}
