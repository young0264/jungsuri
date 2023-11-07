package com.app.jungsuri.domain.tag.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static jooq.dsl.Tables.POST_TAG;

@RequiredArgsConstructor
public class PostTagReadRepositoryImpl implements PostTagReadRepository{

    private final DSLContext dslContext;

    public void deleteByPostEntity(Long postId) {
        dslContext.deleteFrom(POST_TAG)
                .where(POST_TAG.POST_ENTITY_ID.eq(postId))
                .execute();
    }


}
