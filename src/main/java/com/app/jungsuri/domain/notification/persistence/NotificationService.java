package com.app.jungsuri.domain.notification.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationEntity> getNotificationEntitiesByAccountId(Long id) {
        return notificationRepository.findUncheckedByAccountId(id);
    }


    public void checkNotification(Long notificationId) {
        NotificationEntity notificationEntity = notificationRepository.findById(notificationId).orElseThrow(null);
        notificationEntity.check();
    }
}
