package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import static jooq.dsl.tables.MountainTag.MOUNTAIN_TAG;

@Repository
@RequiredArgsConstructor
public class MountainTagReadRepositoryImpl implements MountainTagReadRepository {

    private final DSLContext dslContext;

    @Override
    public Optional<MountainEntity> save(MountainEntity mountainEntity) {
        return dslContext.insertInto(MOUNTAIN_TAG,
                        MOUNTAIN_TAG.MOUNTAIN_ENTITY_ID).values(mountainEntity.getId())
                .returningResult()
                .fetchOptionalInto(MountainEntity.class);
    }
}
