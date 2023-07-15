package com.app.jungsuri.domain.notification.persistence;


public interface NotificationReadRepository {

    public int findUncheckedNotificationCountById(Long accountId);

}
