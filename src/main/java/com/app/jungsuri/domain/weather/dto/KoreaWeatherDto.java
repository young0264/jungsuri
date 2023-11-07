package com.app.jungsuri.domain.weather.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class KoreaWeatherDto {
//    {"SKY":"3","PTY":"0","RN1":"강수없음","REH":"70","UUU":"0.4","VEC":"215","VVV":"0.6","LGT":"0","WSD":"1","T1H":"10"
    //TODO 하늘상태, 강수형태 -> 변환
    /** 하늘상태(SKY) 코드 : 맑음(1)CLEAR, 구름많음(3)CLOUDS, 흐림(4)CLOUDS */
    private String SKY;

    /** 강수형태(PTY) 코드 : 없음(0), 비(1)RAIN, 비+눈(2)SNOW, 눈(3)SNOW, 소나기(4)RAIN */
    private String PTY;

    //TODO THUNDERSTORM
    /** 낙뢰(LGT) 초단기예보 코드: 확률없음(0), 낮음(1), 보통(2), 높음(3) */
    private String LGT;

    private String RN1;

    /** 습도(REH) */
    private String REH;

    /** 동서바람성분(UUU) */
    private String UUU;

    /** 풍향(VEC) */
    private String VEC;

    /** 남북바람성분(VVV) */
    private String VVV;

    /** 풍속(WSD) */
    private String WSD;

    /** 기온(T1H) */
    private String T1H;

}
