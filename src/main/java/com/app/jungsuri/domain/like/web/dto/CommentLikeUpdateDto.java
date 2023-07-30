package com.app.jungsuri.domain.like.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentLikeUpdateDto {
    private Long accountId;
    private Long commentId;
}
