package com.app.jungsuri.domain.post.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class PostSearchDto {

    @JsonProperty("searchTags")
    private List<String> searchTags;

    @Override
    public String toString() {
         return "PostSearchDto{" +
                "searchTags=" + searchTags.toString() +
                '}';
    }
}
