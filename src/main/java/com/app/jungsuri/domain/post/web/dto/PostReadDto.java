package com.app.jungsuri.domain.post.web.dto;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReadDto {
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
