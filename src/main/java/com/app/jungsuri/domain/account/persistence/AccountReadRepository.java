package com.app.jungsuri.domain.account.persistence;

import java.util.List;

public interface AccountReadRepository {
    List<AccountEntity> findAllPostCreatedCheckedIsFalse();

}
