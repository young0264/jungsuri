package com.app.jungsuri.domain.account.web.form;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserAccount extends User {

    private AccountEntity accountEntity;

    public UserAccount(AccountEntity accountEntity) {
        super(accountEntity.getLoginId(), accountEntity.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.accountEntity = accountEntity;
    }

}
