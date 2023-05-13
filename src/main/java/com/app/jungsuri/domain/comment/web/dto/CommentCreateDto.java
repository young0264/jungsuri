package com.app.jungsuri.domain.comment.web.dto;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.post.persistence.PostEntity;
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

    String content;
    PostEntity postEntity;

    AccountEntity accountEntity;

    Integer commentCount;

    public Comment toComment(AccountEntity accountEntity) {
        return new Comment(null, content, accountEntity.getName(), commentCount, LocalDateTime.now(), null, postEntity, accountEntity);
    }

}
