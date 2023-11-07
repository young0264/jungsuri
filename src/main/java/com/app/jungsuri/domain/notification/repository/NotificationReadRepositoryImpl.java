package com.app.jungsuri.domain.notification.repository;

import com.app.jungsuri.domain.notification.model.NotificationEntity;
import jooq.dsl.tables.records.NotificationRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import java.util.List;
import static jooq.dsl.tables.Notification.NOTIFICATION;

@Repository
@RequiredArgsConstructor
public class NotificationReadRepositoryImpl implements NotificationReadRepository {

    private final DSLContext dslContext;

    @Override
    public int findUncheckedNotificationCountById(Long accountId) {
        return dslContext.fetchCount(DSL.selectFrom(NOTIFICATION)
                .where(NOTIFICATION.ACCOUNT_ENTITY_ID.eq(accountId)
                        , NOTIFICATION.CHECKED.eq(false)));
    }

    @Override
    public List<NotificationEntity> findUncheckedByAccountId(Long id) {
        Result<NotificationRecord> result = dslContext.selectFrom(NOTIFICATION)
                .where(NOTIFICATION.ACCOUNT_ENTITY_ID.eq(id)
                    , NOTIFICATION.CHECKED.eq(false))
                .fetch();
        return result.into(NotificationEntity.class);
    }
}
