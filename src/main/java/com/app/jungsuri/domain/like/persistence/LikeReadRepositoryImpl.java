package com.app.jungsuri.domain.like.persistence;

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
                        .where(LIKES.ACCOUNT_ENTITY_ID.eq(accountId), LIKES.POST_ENTITY_ID.eq(postId))
        );
    }

}
