package com.app.jungsuri.domain.like.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static jooq.dsl.tables.Likes.LIKES;

@RequiredArgsConstructor
public class LikeWriteRepositoryImpl implements LikeWriteRepository{

    private final DSLContext dslContext;

    @Override
    public void deleteLikeByPostId(Long accountId, Long postId) {
        dslContext.delete(LIKES)
                .where(LIKES.ACCOUNT_ENTITY_ID.eq(accountId), LIKES.POST_ENTITY_ID.eq(postId))
                .execute();
    }

    @Override
    public void deleteLikeByCommentId(Long accountId, Long commentId) {
        dslContext.delete(LIKES)
                .where(LIKES.ACCOUNT_ENTITY_ID.eq(accountId), LIKES.COMMENT_ENTITY_ID.eq(commentId))
                .execute();
    }
}
