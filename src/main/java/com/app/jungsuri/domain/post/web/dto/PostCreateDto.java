package com.app.jungsuri.domain.post.web.dto;


import com.app.jungsuri.domain.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {

    String title;
    String content;
    String author;
    String imagePath;
    Integer commentCount = 0;

    public Post toPost(String loginId) {
        return new Post(null, title, content, imagePath, author , commentCount, LocalDateTime.now(), null, loginId);
    }
}
