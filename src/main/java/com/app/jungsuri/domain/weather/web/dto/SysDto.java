package com.app.jungsuri.domain.weather.web.dto;

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

    /** 일출시간 ex) '오전 hh:mm */
    private String sunriseTime;

    /** 일몰시간 ex) '오후 hh:mm */
    private String sunsetTime;

}
