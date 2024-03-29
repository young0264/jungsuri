package com.app.jungsuri.domain.account.dto;

import com.app.jungsuri.domain.account.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    @NotBlank
//    @Length(min=3, max=20)
//    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String loginId;

    @Email
//    @NotBlank
    private String email;

    @NotBlank
//    @Length(min=8 , max=50)
    private String password;

    private String name;

    private String bio;

    private String location;

    //TODO 관리자페이지에서 role 수정
    private UserRole userRole = UserRole.USER;

}
