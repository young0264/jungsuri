package com.app.jungsuri.domain.like.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostLikeUpdateDto {
    private Long accountId;
    private Long postId;
}
