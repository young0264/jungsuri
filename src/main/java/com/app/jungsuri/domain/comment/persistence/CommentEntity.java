package com.app.jungsuri.domain.comment.persistence;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String content;

    private String author;

    private Integer likeCount = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private PostEntity postEntity;

    @ManyToOne
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
