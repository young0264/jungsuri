package com.jungsuri.jungsuri.domain.security;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"); //user

    MemberRole(String value) {
        this.value = value;
    }

    private String value;

}
