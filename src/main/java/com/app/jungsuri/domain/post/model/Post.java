package com.app.jungsuri.domain.post.model;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.infra.config.AppConfig.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@RequiredArgsConstructor
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
