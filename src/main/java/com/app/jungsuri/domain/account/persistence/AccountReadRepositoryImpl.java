package com.app.jungsuri.domain.account.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.dsl.tables.Account.ACCOUNT;

@Repository
@RequiredArgsConstructor
public class AccountReadRepositoryImpl implements AccountReadRepository {

    private final DSLContext dslContext;
    @Override
    public List<AccountEntity> findAllPostCreatedCheckedIsFalse() {
        Result<Record> fetch = dslContext.select()
                .from(ACCOUNT)
                .where(ACCOUNT.POST_CREATED_CHECKED.eq(false))
                .fetch();
        return fetch.into(AccountEntity.class);
    }
    @Override
    public Long findIdByLoginId(String loginId){
        return dslContext.select(ACCOUNT.ID)
                .from(ACCOUNT)
                .where(ACCOUNT.LOGIN_ID.eq(loginId))
                .fetchOneInto(Long.class);
    }

    @Override
    public List<String> findAllUsersLoginId() {
        return dslContext.select(ACCOUNT.LOGIN_ID)
                .from(ACCOUNT)
                .fetchInto(String.class);
    }
}
