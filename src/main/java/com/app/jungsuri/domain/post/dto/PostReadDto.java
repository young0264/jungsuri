package com.app.jungsuri.domain.post.dto;

import com.app.jungsuri.domain.account.model.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReadDto {

    @Builder
    public PostReadDto(Long id, String title, String content, String imagePath, LocalDateTime createdAt, LocalDateTime updatedAt, String loginId, String author, AccountEntity accountEntity, Integer commentCount, Long likeCount, Long viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.loginId = loginId;
        this.author = author;
        this.accountEntity = accountEntity;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    private Long id;

    private String title;

    private String content;

    private String imagePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String loginId;

    private String author;

    private AccountEntity accountEntity;

    private Integer commentCount;

    private Long likeCount;

    private Long viewCount;
}
