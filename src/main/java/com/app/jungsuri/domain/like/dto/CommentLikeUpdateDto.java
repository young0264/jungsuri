package com.app.jungsuri.domain.like.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeUpdateDto {
    private Long accountId;
    private Long postId;
    private Long commentId;
}
