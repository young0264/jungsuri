package com.app.jungsuri.domain.account.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountEntity is a Querydsl query type for AccountEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountEntity extends EntityPathBase<AccountEntity> {

    private static final long serialVersionUID = -109648563L;

    public static final QAccountEntity accountEntity = new QAccountEntity("accountEntity");

    public final StringPath bio = createString("bio");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> emailCheckTokenGeneratedAt = createDateTime("emailCheckTokenGeneratedAt", java.time.LocalDateTime.class);

    public final StringPath emailToken = createString("emailToken");

    public final BooleanPath emailVerified = createBoolean("emailVerified");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> joinedAt = createDateTime("joinedAt", java.time.LocalDateTime.class);

    public final StringPath location = createString("location");

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath occupation = createString("occupation");

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final StringPath role = createString("role");

    public QAccountEntity(String variable) {
        super(AccountEntity.class, forVariable(variable));
    }

    public QAccountEntity(Path<? extends AccountEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountEntity(PathMetadata metadata) {
        super(AccountEntity.class, metadata);
    }

}

