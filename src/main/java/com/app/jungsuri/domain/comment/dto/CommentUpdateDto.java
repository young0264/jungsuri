package com.app.jungsuri.domain.comment.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {

    @NotNull(message = "commentId is null")
    Long commentId;

    @NotNull(message="댓글을 작성해주세요.")
    @Size(min = 5, max = 50, message="댓글은 5자 이상 50자 이하로 작성해주세요.")
    String newComment;
}
