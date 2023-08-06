package com.app.jungsuri.domain.like.persistence;

import jooq.dsl.enums.LikesType;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static jooq.dsl.tables.Likes.LIKES;

@RequiredArgsConstructor
public class LikeReadRepositoryImpl implements LikeReadRepository {

    private final DSLContext dslContext;


    @Override
    public boolean isCheckedPostLike(Long accountId, Long postId) {
        return dslContext.fetchExists(
                dslContext.selectOne()
                        .from(LIKES)
                        .where(LIKES.TYPE.equal(LikesType.POST), LIKES.ACCOUNT_ENTITY_ID.eq(accountId), LIKES.POST_ENTITY_ID.eq(postId))
        );
    }

    @Override
    public boolean isCheckedCommentLike(Long accountId, Long commentId) {
        return dslContext.fetchExists(
                dslContext.selectOne()
                        .from(LIKES)
                        .where(LIKES.TYPE.equal(LikesType.COMMENT), LIKES.ACCOUNT_ENTITY_ID.eq(accountId), LIKES.COMMENT_ENTITY_ID.eq(commentId))
        );
    }
}
