package com.app.jungsuri.domain.post.web.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostCreateDto {

    @Size(min = 5, max = 50, message="제목은 5자 이상 50자 이하로 작성해주세요.")
    String title;
    String content;
    String author;
    String imagePath;
    Integer commentCount = 0;
    MultipartFile imgFile;

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

    public void initImagePath(String imagePath) {
        if (imagePath == null || imagePath.equals("") ) {
            this.imagePath = "/images/jungsuri_logo.png";
        } else {
            this.imagePath = imagePath;
        }
    }
}
