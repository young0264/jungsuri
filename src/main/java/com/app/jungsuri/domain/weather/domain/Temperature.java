package com.app.jungsuri.domain.weather.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**온도 정보*/

@Getter
@Setter
@Builder
@ToString
public class Temperature {

    /** 온도. 단위 기본값 : 켈빈, 미터법 : 섭씨, 임페리얼 : 화씨 */
    private float temp;

    /** 온도. 단위 기본값 : 켈빈, 미터법 : 섭씨, 임페리얼 : 화씨 */
    private float feelsLike;

    /** 현재 최저 온도.(대규모 대도시 및 도시 지역 내) */
    private float tempMin;

    /** 현재 최대 온도.(대규모 대도시 및 도시 지역 내)*/
    private float tempMax;
}
