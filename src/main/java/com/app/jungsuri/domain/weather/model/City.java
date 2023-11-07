package com.app.jungsuri.domain.weather.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class City {

    /** 도시 ID */
    private long id;

    /** 도시 이름 */
    private String name;
}
