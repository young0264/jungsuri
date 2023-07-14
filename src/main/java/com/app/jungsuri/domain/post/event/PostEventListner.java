package com.app.jungsuri.domain.post.event;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.domain.notification.persistence.NotificationEntity;
import com.app.jungsuri.domain.notification.persistence.NotificationRepository;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
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
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;


    @EventListener
    public void handlePostCreatedEvent(PostCreatedEvent postCreatedEvent) {
        log.info("handlePostCreatedEvent");
        accountRepository.findAllPostCreatedCheckedIsFalse().forEach(accountEntity -> {
            log.info(accountEntity.getEmail());
            notificationRepository.save(createNotification(accountEntity,postCreatedEvent.getPostEntity()));
        });
    }

    private NotificationEntity createNotification(AccountEntity accountEntity, PostEntity postEntity) {
        return NotificationEntity.builder()
                .accountEntity(accountEntity)
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .checked(false)
                .type("postCreated")
                .createdAt(postEntity.getCreatedAt())
                .build();
    }
}
