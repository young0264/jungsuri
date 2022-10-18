package com.jungsuri.jungsuri.dto.Member;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
