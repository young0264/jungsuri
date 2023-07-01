package com.app.jungsuri.domain.account.web.form;

import lombok.Getter;

@Getter
public enum AccountGrade {
    ADMIN("ADMIN","관리자"), REGURAL_MEMBER("Regural","정회원"), ASSOCIATE_MEMBER("Associate","비회원"), BLACKLIST("BlackList","블랙리스트");

    private final String code;
    private final String description;
    AccountGrade(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
