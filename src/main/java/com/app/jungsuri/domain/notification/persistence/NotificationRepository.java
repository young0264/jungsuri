package com.app.jungsuri.domain.notification.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, NotificationReadRepository {

}
