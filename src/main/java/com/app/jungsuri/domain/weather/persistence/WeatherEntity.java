package com.app.jungsuri.domain.weather.persistence;

import com.app.jungsuri.domain.weather.domain.*;
import lombok.Data;

import java.util.List;

/**
 * OpenWeath API - 현재 날씨 데이터
 */
@Data
public class WeatherEntity {

    private List<Weather> weather;

    /** 내부 매개 변수 */
    private String base;

    private Main main;

    private Wind wind;

    private Clouds clouds;

    private Rain rain;

    private Snow snow;

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
