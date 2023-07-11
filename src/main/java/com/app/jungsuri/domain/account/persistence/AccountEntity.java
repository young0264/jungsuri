package com.app.jungsuri.domain.account.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@EqualsAndHashCode(of = "id")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    private String name;

    private String role; //TODO ENUM으로 -> 관리자가 부여해주는 방식

    @Column(unique = true)
//    @NotBlank
    private String email;

    private String emailToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private boolean emailVerified;

    private String location; //TODO 대략적인 시 or 구까지.

    private String occupation;//

    @Column(unique = true)
    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    private String profileImage;

    private LocalDateTime joinedAt;

    public boolean isValidToken(String token) {
        return this.emailToken.equals(token);
    }

    public void completeSignUp() {
        this.emailVerified = true;
        joinedAt = LocalDateTime.now();
    }

    public void generateEmailCheckToken() {
        this.emailToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1)); //1시간 이전에 만들었는지
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
