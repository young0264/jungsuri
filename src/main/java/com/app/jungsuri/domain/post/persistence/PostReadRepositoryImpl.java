package com.app.jungsuri.domain.post.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.app.jungsuri.domain.post.persistence.QPostEntity.postEntity;

@Repository
public class PostReadRepositoryImpl implements PostReadRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostReadRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PostEntity findPostEntityById(Long id) {
        return jpaQueryFactory.selectFrom(postEntity)
                .where(postEntity.id.eq(id))
                .fetchOne();
    }
}

