package com.app.jungsuri.domain.tag.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountTagReadRepositoryImpl implements AccountTagReadRepository {

    private final DSLContext dslContext;

//    @Override //tagid, accountid
//    public Optional<AccountEntity> save(AccountTag accountTag, Long accountId) {
//        log.info("account tag save 시작");
//        log.info("accountTag: {}", accountTag);
//
//        return dslContext.insertInto(ACCOUNT_TAG,
//                        ACCOUNT_TAG.ACCOUNT_ENTITY_ID, ACCOUNT_TAG.ID)
//                .values(accountId, accountTag.getId())
//                .returningResult()
//                .fetchOptionalInto(AccountEntity.class);
//    }
}
