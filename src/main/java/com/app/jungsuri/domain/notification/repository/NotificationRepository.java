package com.app.jungsuri.domain.notification.repository;

import com.app.jungsuri.domain.notification.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, NotificationReadRepository {

}
