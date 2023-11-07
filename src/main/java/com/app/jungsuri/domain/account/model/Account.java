package com.app.jungsuri.domain.account.model;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Account {


    private final Long id;

    private final String bio;

    private final String username;

    private final String role; //TODO ENUM으로

    private final String email;

    private final String emailToken;

    private final LocalDateTime emailCheckTokenGeneratedAt;

    private final boolean emailVerified;

    private final String location; //TODO 대략적인 시 or 구까지.

    private final String occupation; //

    private final String loginId;

    private final String password;

    private final String nickname;

    private final String profileImage;

    private final LocalDateTime joinedAt;

    ModelMapper modelMapper = new ModelMapper();


    @Builder
    public AccountEntity toEntity() {
        return modelMapper.map(this, AccountEntity.class);
    }

}
