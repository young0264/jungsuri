package com.app.jungsuri.domain.notification.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
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

    @Column(name="content", nullable = false)
    private String content;

    private boolean checked = false;

    private String type;

    private LocalDateTime createdAt;

    @ManyToOne
    private AccountEntity accountEntity;




}
