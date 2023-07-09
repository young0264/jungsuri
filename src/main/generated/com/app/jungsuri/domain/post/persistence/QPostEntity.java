package com.app.jungsuri.domain.post.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostEntity is a Querydsl query type for PostEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostEntity extends EntityPathBase<PostEntity> {

    private static final long serialVersionUID = 129537229L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostEntity postEntity = new QPostEntity("postEntity");

    public final com.app.jungsuri.domain.account.persistence.QAccountEntity accountEntity;

    public final StringPath author = createString("author");

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final ListPath<com.app.jungsuri.domain.comment.persistence.CommentEntity, com.app.jungsuri.domain.comment.persistence.QCommentEntity> commentList = this.<com.app.jungsuri.domain.comment.persistence.CommentEntity, com.app.jungsuri.domain.comment.persistence.QCommentEntity>createList("commentList", com.app.jungsuri.domain.comment.persistence.CommentEntity.class, com.app.jungsuri.domain.comment.persistence.QCommentEntity.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QPostEntity(String variable) {
        this(PostEntity.class, forVariable(variable), INITS);
    }

    public QPostEntity(Path<? extends PostEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostEntity(PathMetadata metadata, PathInits inits) {
        this(PostEntity.class, metadata, inits);
    }

    public QPostEntity(Class<? extends PostEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountEntity = inits.isInitialized("accountEntity") ? new com.app.jungsuri.domain.account.persistence.QAccountEntity(forProperty("accountEntity")) : null;
    }

}

