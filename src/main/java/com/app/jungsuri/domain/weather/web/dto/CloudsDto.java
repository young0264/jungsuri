package com.app.jungsuri.domain.weather.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CloudsDto {

    /** 흐림, % */
    private int all;

    private float speed; //test

}
