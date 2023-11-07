package com.app.jungsuri.domain.like.model;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.comment.model.CommentEntity;
import com.app.jungsuri.domain.post.model.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name="likes")
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AccountEntity accountEntity;

    @JoinColumn(nullable = true)
    @ManyToOne
    private PostEntity postEntity;

    @JoinColumn(nullable = true)
    @ManyToOne
    private CommentEntity commentEntity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LikeType type;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;
}
