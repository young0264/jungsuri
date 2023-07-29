package com.app.jungsuri.domain.weather.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Clouds {

    /** 흐림정도 %, 구름속도 */
    private int cloudiness;

    private float speed;

}
