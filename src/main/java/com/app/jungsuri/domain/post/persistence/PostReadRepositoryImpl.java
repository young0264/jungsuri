package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.infra.pagination.PostPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import static jooq.dsl.tables.Post.POST;
import static jooq.dsl.tables.PostTag.POST_TAG;
import static jooq.dsl.tables.Tag.TAG;

@Slf4j
@Repository //제거 혹은 repository라는 것을 알려주는 용도의 annotation
@RequiredArgsConstructor
public class PostReadRepositoryImpl implements PostReadRepository {
    private final DSLContext dslContext;

    /** 현재 등록된 모든 게시물의 갯수를 반환 */
    @Override
    public int getPostCount() {
        return dslContext.selectCount()
                .from(POST)
                .fetchOneInto(Integer.class);
    }

    /** id에 해당하는 post 가져오기 */
    @Override
    public PostEntity findPostEntityById(Long id) {
        return dslContext.select()
                        .from(POST)
                        .where(POST.ID.eq(id))
                        .fetchOneInto(PostEntity.class);
    }


    /** Tag에 해당하는 post 가져오기 */
    @Override
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

    /** 좋아요 갯수가 많은 5개의 게시글 가져오기 */
    @Override
    public List<PostEntity> findTop5ByLikeCountAsc() {
        return dslContext.select()
                .from(POST)
                .orderBy(POST.LIKE_COUNT.desc())
                .limit(5)
                .fetchInto(PostEntity.class);
    }

    /** pagination 적용된 post list 가져오기 */
    @Override
    public List<PostEntity> findPostListByPagination(int startRowNum) {
        return dslContext.select()
                .from(POST)
                .orderBy(POST.CREATED_AT.desc())
                .limit(startRowNum-1, PostPage.PAGE_ROW_SIZE.getValue())
                .fetchInto(PostEntity.class);
    }

    /** 최근 게시글 3개 가져오기 */
    @Override
    public List<PostEntity> findPostListByRecentTop3() {
        return dslContext.select()
                .from(POST)
                .orderBy(POST.CREATED_AT.desc())
                .limit(3)
                .fetchInto(PostEntity.class);
    }
}

