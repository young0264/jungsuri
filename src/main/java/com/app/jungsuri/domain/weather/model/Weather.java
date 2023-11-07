package com.app.jungsuri.domain.weather.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Weather {

    /** 기상 조건 ID */
    private int id;

    /** 날씨 매개 변수 그룹 (비, 눈, 극한 등) -> 날씨상태 (영어) */
    private String statusEn;

    /** 그룹 내 날씨 조건 -> 날씨상태 (한국어) */
    private String statusKo;

    /** 날씨 아이콘 ID */
    private String iconId;

    /** 날씨 이미지 경로 **/
    private String imgSrc;
}
