package com.app.jungsuri.domain.account.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MountainExpUpdateDto {

    @NotBlank
    private String mountainName;

    @JsonProperty("LoginIdArr")
    @NotBlank
    private List<String> LoginIdArr;

    @Override
    public String toString() {
        return "MountainExpUpdateDto{" +
                "mountainName='" + mountainName + '\'' +
                ", LoginIds=" + LoginIdArr +
                '}';
    }

}
