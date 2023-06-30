package com.app.jungsuri.domain.comment.persistence;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 5, max = 50)
    private String content;

    private String author;

    private Integer likeCount;

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
