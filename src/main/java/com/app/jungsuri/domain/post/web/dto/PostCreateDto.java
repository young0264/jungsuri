package com.app.jungsuri.domain.post.web.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {

    String title;
    String content;
    String author;
    String imagePath;
    Integer commentCount = 0;

    @JsonProperty("tagList")
    List<String> tagList ;

}
