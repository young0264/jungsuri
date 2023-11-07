package com.app.jungsuri.domain.post.event;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.notification.enums.NotificationType;
import com.app.jungsuri.domain.notification.model.NotificationEntity;
import com.app.jungsuri.domain.notification.repository.NotificationRepository;
import com.app.jungsuri.domain.post.model.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Async
@Component
@Transactional
@RequiredArgsConstructor
public class PostEventListner {

    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;


    @EventListener
    public void handlePostCreatedEvent(PostCreatedEvent postCreatedEvent) {
        accountRepository.findAllPostCreatedCheckedIsFalse().forEach(accountEntity -> {
            notificationRepository.save(createNotification(accountEntity,postCreatedEvent.getPostEntity()));
        });
    }

    private NotificationEntity createNotification(AccountEntity accountEntity, PostEntity postEntity) {
        return NotificationEntity.builder()
                .accountEntity(accountEntity)
                .title(postEntity.getTitle())
                .message(NotificationType.getMessgeByType(NotificationType.POST_CREATED))
                .checked(false)
                .link("localhost:8080/notification/check")
                .type(NotificationType.POST_CREATED)
                .createdAt(postEntity.getCreatedAt())
                .build();
    }
}
