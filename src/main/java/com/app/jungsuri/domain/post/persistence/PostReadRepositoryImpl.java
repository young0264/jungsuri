package com.app.jungsuri.domain.post.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import static jooq.dsl.tables.Post.POST;
import static jooq.dsl.tables.PostTag.POST_TAG;
import static jooq.dsl.tables.Tag.TAG;

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

    public List<PostEntity> findAllByTags(List<String> searchTags) {
        var tagSubQuery = dslContext.select(TAG.ID)
                .from(TAG)
                .where(TAG.NAME.in(searchTags));
        var postSubQuery =  dslContext.select(POST_TAG.POST_ENTITY_ID)
                .from(POST_TAG)
                .where(POST_TAG.TAG_ID.in(tagSubQuery));
        return dslContext.select()
                .from(POST)
                .where(POST.ID.in(postSubQuery))
                .fetchInto(PostEntity.class);
    }

    public List<PostEntity> findTop5ByLikeCountAsc() {
        return dslContext.select()
                .from(POST)
                .orderBy(POST.LIKE_COUNT.desc())
                .limit(5)
                .fetchInto(PostEntity.class);
    }

}

