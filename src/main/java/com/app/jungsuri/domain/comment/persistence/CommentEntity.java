package com.app.jungsuri.domain.comment.persistence;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
}
