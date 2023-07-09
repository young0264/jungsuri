package com.app.jungsuri.domain.post.model;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    private final Long id;
    private final String title;
    private final String content;
    private final String imagePath;
    private final String author;
    private final Integer commentCount;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String loginId;

//    private final String author;
//
//    private final AccountEntity accountEntity;
//
//    private final Long likeCount;
//
//    private final Long commentCount;
//
//    private final Long viewCount;


    @Builder
    public Post(Long id, String title, String content, String imagePath, String author, Integer commentCount, LocalDateTime createdAt, LocalDateTime updatedAt, String loginId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.author = author;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.loginId = loginId;
    }

    public PostEntity toEntity(){
        return PostEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .author(author)
                .commentCount(commentCount)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .loginId(loginId)
//                .account_id(account_id)
//                .likeCount(likeCount)
//                .commentCount(commentCount)
//                .viewCount(viewCount)
                .build();
    }



}
