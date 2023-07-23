package com.app.jungsuri.domain.account.web;

public enum UserRole {
    USER, ADMIN;

    public String getRole() {
        return this.name();
    }
}
