package com.jungsuri.jungsuri.dto.Member;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMemberDto {
    @Size(min = 5, max = 20, message = "닉네임 길이는 5이상 20이하여야 합니다" )
    @NotEmpty(message = "사용자 ID는 필수입니다")
    private String username;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password1;

    @NotEmpty(message = "비밀번호는 확인은 필수입니다")
    private String password2;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

}
