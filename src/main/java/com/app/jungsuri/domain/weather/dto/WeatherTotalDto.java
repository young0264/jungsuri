package com.app.jungsuri.domain.weather.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WeatherTotalDto {

    private List<WeatherDto> weather;

    private SysDto sys;

    /** 내부 매개 변수 */
    private String base;

    private MainDto main;

    private WindDto wind;

    private CloudsDto clouds;

    private RainDto rain;

    private SnowDto snow;

    /** 가시성 */
    private int visibility;

    /** 데이터 계산 시간, 유닉스, UTC */
    private long dt;

    /** UTC에서 초 단위로 이동 */
    private int timezone;

    /** 도시 ID */
    private long id;

    /** 도시 이름 */
    private String name;

    /** 내부 매개 변수 */
    private int cod;

}
