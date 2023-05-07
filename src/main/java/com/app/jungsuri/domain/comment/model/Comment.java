package com.app.jungsuri.domain.comment.model;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Comment {

    private final Long id;

    private final String content;

    private final String author;

    private final Long likeCount;

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

}
