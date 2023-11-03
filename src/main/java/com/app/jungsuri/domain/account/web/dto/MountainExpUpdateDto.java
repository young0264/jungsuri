package com.app.jungsuri.domain.account.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MountainExpUpdateDto {

    @NotBlank
    private String mountainName;

    @JsonProperty("LoginIdArr")
    @NotBlank
    private List<String> LoginIdArr;

    @NotBlank
    private String hikingDate;

}
