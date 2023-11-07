package com.app.jungsuri.domain.comment.model;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Comment {

    private final Long id;

    private final String content;

    private final String author;

    private final Integer likeCount;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private final PostEntity postEntity;

    private final AccountEntity accountEntity;


    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .id(id)
                .content(content)
                .author(author)
                .likeCount(likeCount)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .postEntity(postEntity)
                .accountEntity(accountEntity)
                .build();
    }

    public String toString() {
        return "Comment(id=" + this.getId() + ", content=" + this.getContent() + ", author=" + this.getAuthor() + ", likeCount=" + this.getLikeCount() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ", postEntity=" + this.getPostEntity() + ", accountEntity=" + this.getAccountEntity() + ")";
    }

}
