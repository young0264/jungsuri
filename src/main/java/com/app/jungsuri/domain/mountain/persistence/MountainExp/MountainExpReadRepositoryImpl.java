package com.app.jungsuri.domain.mountain.persistence.MountainExp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import static jooq.dsl.tables.Mountain.MOUNTAIN;
import static jooq.dsl.tables.MountainExp.*;
import static org.jooq.impl.DSL.sum;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MountainExpReadRepositoryImpl implements MountainExpReadRepository{

    private final DSLContext dslContext;

    @Override
    public Optional<Integer> get100MountainExp(Long accountId) {
        return dslContext
                        .select(sum(MOUNTAIN.HEIGHT))
                            .from(MOUNTAIN)
                                .where(MOUNTAIN.ID.in(
                                        dslContext.select(MOUNTAIN_EXP.MOUNTAIN_ENTITY_ID)
                                                .from(MOUNTAIN_EXP)
                                                .where(MOUNTAIN_EXP.ACCOUNT_ENTITY_ID.eq(accountId))

                                )).fetchOptionalInto(Integer.class);
    }

}
