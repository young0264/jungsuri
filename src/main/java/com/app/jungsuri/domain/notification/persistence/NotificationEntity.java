package com.app.jungsuri.domain.notification.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.notification.model.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name="NOTIFICATION")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="message", nullable = false)
    private String message;

    @Builder.Default
    private boolean checked = false;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String link;

    private LocalDateTime createdAt;

    @ManyToOne
    private AccountEntity accountEntity;

    public void check() {
        this.checked = true;
    }
}
