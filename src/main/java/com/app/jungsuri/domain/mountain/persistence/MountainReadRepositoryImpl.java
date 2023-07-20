package com.app.jungsuri.domain.mountain.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;

import static jooq.dsl.tables.Mountain.MOUNTAIN;

@Repository
@RequiredArgsConstructor
public class MountainReadRepositoryImpl implements MountainReadRepository {

    private final DSLContext dslContext;

    @Override
    public List<MountainEntity> findAllMountains() {
        return dslContext.select()
                .from(MOUNTAIN)
                .fetchInto(MountainEntity.class);
    }


}
