package com.app.jungsuri.domain.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Snow {
    /** 지난 1 시간 동안의 눈량, mm */
    @JsonProperty("1h")
    private float snow1h;

    /** 지난 3 시간 동안의 눈량, mm */
    @JsonProperty("3h")
    private float snow3h;
}
