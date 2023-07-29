package com.app.jungsuri.domain.weather.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Wind {
    /** 바람의 속도. 단위 기본값 : meter/sec, 미터법 : meter/sec, 임페리얼 : miles/hour */
    private float speed;

    /** 풍향,도 (기상) */
    private int direction ;

//    /**  바람 돌풍. 단위 기본값 : meter/sec, 미터법 : meter/sec, 임페리얼 : miles/hour */
    private float gust;
}
