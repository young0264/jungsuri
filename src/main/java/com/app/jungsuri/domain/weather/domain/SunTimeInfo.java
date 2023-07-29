package com.app.jungsuri.domain.weather.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SunTimeInfo {
    private int type;

    private int id;

    /** 국가 코드 (GB, JP, KR ...등등) */
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
