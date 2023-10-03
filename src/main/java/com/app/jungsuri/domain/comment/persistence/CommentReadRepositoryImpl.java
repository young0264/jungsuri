package com.app.jungsuri.domain.comment.persistence;

import jooq.dsl.tables.Comment;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.dsl.tables.Comment.COMMENT;

@Repository
@RequiredArgsConstructor
public class CommentReadRepositoryImpl implements CommentReadRepository{
    private final DSLContext dslContext;

    /** 최근 등록된 댓글 5개 가져오기 */
    public List<CommentEntity> findTop5ByOrderByCreatedAtDesc() {
        return dslContext.select()
                .from(COMMENT)
                .orderBy(COMMENT.CREATED_AT.desc())
                .limit(5)
                .fetchInto(CommentEntity.class);
    }

}
