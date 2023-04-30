package com.app.jungsuri.domain.account.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    private String username;

    private String role; //TODO ENUM으로

    @Column(unique = true)
    private String email;

    private String emailToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private boolean emailVerified;

    private String location; //TODO 대략적인 시 or 구까지.

    private String occupation;//

    @Column(unique = true)
    private String loginId;

    private String password;

    @Column(unique = true)
    private String nickname;

    private String profileImage;

    private LocalDateTime joinedAt;
}
