package com.app.jungsuri.domain.notification.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static jooq.dsl.tables.Notification.NOTIFICATION;

@Repository
@RequiredArgsConstructor
public class NotificationReadRepositoryImpl implements NotificationReadRepository {

    private final DSLContext dslContext;

    @Override
    public int findUncheckedNotificationCount(Long accountId) {
        return dslContext.fetchCount(dslContext.selectCount()
                .from(NOTIFICATION)
                .where(NOTIFICATION.ACCOUNT_ENTITY_ID.eq(accountId)
                        , NOTIFICATION.CHECKED.eq(false)));
    }
}
