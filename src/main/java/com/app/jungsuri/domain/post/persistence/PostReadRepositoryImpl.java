package com.app.jungsuri.domain.post.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static jooq.dsl.tables.Post.POST;

@Repository
@RequiredArgsConstructor
public class PostReadRepositoryImpl implements PostReadRepository {
    private final DSLContext dslContext;

    public PostEntity findPostEntityById(Long id) {
        return dslContext.select()
                        .from(POST)
                        .where(POST.ID.eq(id))
                        .fetchOneInto(PostEntity.class);
    }

}

