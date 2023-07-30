package com.app.jungsuri.domain.post.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static jooq.dsl.tables.Post.POST;

@Repository //제거 혹은 repository라는 것을 알려주는 용도의 annotation
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

