package com.app.jungsuri.domain.weather.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sys {
    private int type;

    private int id;

    /** 국가 코드 (GB, JP 등) */
    private String country;

    /** 일출 시간, 유닉스, UTC */
    private long sunrise;

    /** 일몰 시간, 유닉스, UTC */
    private long sunset;
}
