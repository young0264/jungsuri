package com.app.jungsuri.domain.post.web.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostCreateDto {

    String title;
    String content;
    String author;
    String imagePath;
    Integer commentCount = 0;

    @JsonProperty("tagList")
    List<String> tagList ;

    public PostCreateDto(String title, String content, String author, String imagePath, Integer commentCount, List<String> tagList) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imagePath = imagePath;
        this.commentCount = commentCount;
        this.tagList = tagList;
    }
}
