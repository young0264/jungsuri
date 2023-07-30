package com.app.jungsuri.domain.like.persistence;

import jooq.dsl.tables.Account;
import jooq.dsl.tables.Likes;
import jooq.dsl.tables.Post;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@RequiredArgsConstructor
public class LikeReadRepositoryImpl implements LikeReadRepository {

    private final DSLContext dslContext;

    @Override
    public boolean isCheckedLike() {
        dslContext.select()
                .from(Likes.LIKES)

        return true;
    }
}
