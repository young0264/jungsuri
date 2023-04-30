package com.app.jungsuri.domain.account.model;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void Account_toEntity_테스트() {
        Account account = new Account(1L,"2","1","1","1","1", LocalDateTime.now(),true,"1","1","1","1","1","1",LocalDateTime.now());
        AccountEntity accountEntity = account.toEntity();
        System.out.println(account.getBio());
        System.out.println(accountEntity.getBio());
        assertThat(account.toEntity().getBio()).isEqualTo("2");
    }

}