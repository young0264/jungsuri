package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.post.web.dto.PostReadDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.TableLike;
import org.springframework.stereotype.Repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

//@Repository
//@RequiredArgsConstructor
public class PostReadRepository {
//    private final DSLContext dslContext;

//    public PostReadDto getPost(Long id) {
////        dslContext.selectFrom(POST)
////        TableLike<?>[] post;
//        return (PostReadDto) dslContext.select()
//                .from(table("post"))
//                .where(field("id").eq(id)).fetchOne();
////                .where(post.g)
////                .where(post)
////                .fetchOneInto(PostReadDto.class);
//    }

}
