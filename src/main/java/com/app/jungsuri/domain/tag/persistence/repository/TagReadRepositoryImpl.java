package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.Tag;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import java.util.Optional;
import static jooq.dsl.tables.Tag.TAG;

@RequiredArgsConstructor
public class TagReadRepositoryImpl implements TagReadRepository {

    private final DSLContext dslContext;

    public Optional<Tag> findByName(String name) {
         return dslContext.select()
                .from(TAG)
                .where(TAG.NAME.eq(name))
                .fetchOptionalInto(Tag.class);

    }
}
