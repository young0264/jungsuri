package com.app.jungsuri.domain.weather.domain;

import lombok.Data;

@Data
public class Main {
    /** 온도. 단위 기본값 : 켈빈, 미터법 : 섭씨, 임페리얼 : 화씨 */
    private float temp;

    /** 온도. 단위 기본값 : 켈빈, 미터법 : 섭씨, 임페리얼 : 화씨 */
    private float feels_like;

    /** 현재 최저 온도.(대규모 대도시 및 도시 지역 내) */
    private float temp_min;

    /** 현재 최대 온도.(대규모 대도시 및 도시 지역 내)*/
    private float temp_max;

    /** 대기압 (해수면, 해수면 또는 grnd_level 데이터가 없는 경우), hPa */
    private int pressure;

    /** 습도, % */
    private float humidity;

    /** 해수면의 대기압, hPa */
    private float sea_level;

    /** 지면에서의 대기압, hPa */
    private float grnd_level;
}
