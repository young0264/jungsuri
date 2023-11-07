package com.app.jungsuri.domain.comment.dto;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {


    @NotNull(message="댓글을 작성해주세요.")
    @Size(min = 5, max = 50, message="댓글은 5자 이상 50자 이하로 작성해주세요.")
    String content;

    @NotNull(message="로그인 정보가 없습니다.")
    String loginId;

    @NotNull(message="게시글 정보가 없습니다.")
    Long postId;


    public Comment toComment(AccountEntity accountEntity, PostEntity postEntity) {
        return new Comment(null, content, accountEntity.getName(), 0, LocalDateTime.now(), null, postEntity, accountEntity);
    }

}
