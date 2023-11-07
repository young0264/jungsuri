package com.app.jungsuri.domain.weather.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Rain {

    /** 지난 1 시간 동안의 강우량, mm */
    private float rainPast1h;

    /** 지난 3 시간 동안의 강우량, mm */
    private float rainPast3h;
}
