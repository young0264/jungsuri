package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.Tag;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import java.util.Optional;
import static jooq.dsl.Tables.TAG;

@RequiredArgsConstructor
public class TagReadRepositoryImpl implements TagReadRepository{

    private final DSLContext dslContext;

    public Optional<Tag> findByName(String name) {
        Result<Record> result = dslContext.select()
                .from(TAG)
                .where(TAG.NAME.eq(name))
                .fetch();
        if (result.isEmpty()) {
            return Optional.empty();
        }return Optional.of(result.into(Tag.class).get(0));
    }

}
