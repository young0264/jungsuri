package com.app.jungsuri.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateDto {


    @NotBlank
    String loginId;

    @NotBlank
    String currentPassword;

    @NotBlank
    String newPassword;

    @NotBlank
    String newPasswordConfirm;

    @Override
    public String toString() {
        return "PasswordUpdateDto{" +
                "loginId='" + loginId + '\'' +
                ", currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newPasswordConfirm='" + newPasswordConfirm + '\'' +
                '}';
    }
}
