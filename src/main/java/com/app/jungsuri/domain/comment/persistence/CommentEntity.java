package com.app.jungsuri.domain.comment.persistence;


import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="author", nullable = false)
    private String author;

    @Builder.Default
    @Column(name="like_count", nullable = false)
    private Integer likeCount = 0;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = true)
    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name="post_entity_id", nullable = false)
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name="account_entity_id", nullable = false)
    private AccountEntity accountEntity;

    public void updateComment(String newComment) {
        this.content = newComment;
        this.updatedAt = LocalDateTime.now();
    }

    public int increaseLikeCount() {
        return this.likeCount += 1;
    }
    public int decreaseLikeCount() {
        if(this.likeCount == 0) return 0;
        return this.likeCount -= 1;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", likeCount=" + likeCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", postEntity=" + postEntity +
                ", accountEntity=" + accountEntity +
                '}';
    }
}
