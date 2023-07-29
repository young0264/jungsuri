package com.app.jungsuri.domain.weather.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class WeatherConfig {

    /** 날씨 데이터가 어떤 기준으로 제공되었는지, 내부 매개 변수 */
    private String base;

    /**  API 응답의 상태 코드, 내부 매개 변수 */
    private int cod;

    /**  UTC와 특정 지역 간의 시간 차이 */
    private int timezone;

    /** 유닉스 시간, UTC */
    private long dt;

}
