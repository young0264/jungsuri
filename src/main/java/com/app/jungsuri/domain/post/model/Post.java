package com.app.jungsuri.domain.post.model;

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
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String loginId;


    public PostEntity toEntity(){
        return PostEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .author(loginId)
                .build();
    }



}
