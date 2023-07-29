package com.app.jungsuri.domain.weather.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Weather {
    /** 기상 조건 ID */
    private int id;

    /** 날씨 매개 변수 그룹 (비, 눈, 극한 등) */
    private String main;

    /** 그룹 내 날씨 조건 */
    private String description;

    /** 날씨 아이콘 ID */
    private String icon;

    /** 날씨 이미지 경로 **/
    private String imgSrc;
}
