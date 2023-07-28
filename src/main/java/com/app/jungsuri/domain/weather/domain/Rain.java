package com.app.jungsuri.domain.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rain {
    /** 지난 1 시간 동안의 강우량, mm */
    @JsonProperty("1h")
    private float rain1h;

    /** 지난 3 시간 동안의 강우량, mm */
    @JsonProperty("3h")
    private float rain3h;
}
