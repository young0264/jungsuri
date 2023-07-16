package com.app.jungsuri.domain.notification.persistence;

import java.util.List;

public interface NotificationReadRepository {

    public int findUncheckedNotificationCountById(Long accountId);

    public List<NotificationEntity> findUncheckedByAccountId(Long accountId);
}
