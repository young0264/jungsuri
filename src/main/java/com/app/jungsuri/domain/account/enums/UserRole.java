package com.app.jungsuri.domain.account.enums;

public enum UserRole {
    USER, ADMIN;

    public String getRole() {
        return this.name();
    }
}
