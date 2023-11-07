package com.app.jungsuri.domain.account.model;

import com.app.jungsuri.domain.account.model.AccountEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

@Getter
@Slf4j
public class UserAccount extends User {

    private AccountEntity accountEntity;

    public UserAccount(AccountEntity accountEntity) {
        super(accountEntity.getLoginId(), accountEntity.getPassword(), List.of(new SimpleGrantedAuthority(accountEntity.getUserRole().toString())));
        this.accountEntity = accountEntity;
    }

}
