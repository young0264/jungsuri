package com.app.jungsuri.domain.notification.repository;

import com.app.jungsuri.domain.notification.model.NotificationEntity;

import java.util.List;

public interface NotificationReadRepository {

    public int findUncheckedNotificationCountById(Long accountId);

    public List<NotificationEntity> findUncheckedByAccountId(Long accountId);
}
