package com.app.jungsuri.domain.comment.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteDto {
    Long commentId;
    Long postId;
}