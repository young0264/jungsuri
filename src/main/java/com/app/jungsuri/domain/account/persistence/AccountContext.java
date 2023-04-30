package com.app.jungsuri.domain.account.persistence;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    private final AccountEntity accountEntity;

    public AccountContext(AccountEntity accountEntity, Collection<? extends GrantedAuthority> authorities) {
        super(accountEntity.getLoginId(), accountEntity.getPassword(), authorities);
        this.accountEntity = accountEntity;
    }

    public AccountEntity getAccount() {
        return accountEntity;
    }

}
