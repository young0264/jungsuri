package com.app.jungsuri.domain.account.repository;

import com.app.jungsuri.domain.account.model.AccountEntity;

import java.util.List;

public interface AccountReadRepository {
    List<AccountEntity> findAllPostCreatedCheckedIsFalse();

    Long findIdByLoginId(String loginId);

    List<String> findAllUsersLoginId();

}
