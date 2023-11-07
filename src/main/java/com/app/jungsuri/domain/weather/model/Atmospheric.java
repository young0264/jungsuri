package com.app.jungsuri.domain.weather.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 대기 정보 */

@Getter
@Setter
@Builder
@ToString
public class Atmospheric {

    /** 대기압 (해수면, 해수면 또는 grnd_level 데이터가 없는 경우), hPa */
    private int pressure;

    /** 습도, % */
    private float humidity;

    /** 해수면의 대기압, hPa */
    private float seaLevel;

    /** 지면에서의 대기압, hPa */
    private float groundLevel;

    /** 가시성 */
    private int visibility;
}
