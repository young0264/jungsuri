package com.app.jungsuri.domain;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@MockMvcTest
public class jooqExampleTest {

    @Autowired
    private DSLContext create;

    @Autowired
    private PostService postService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    @WithMockUser(username="12", password="12")
    public void test() {
        System.out.println(create.select().from("post").fetch());
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(RuntimeException::new);
        PostEntity post = postService.createPost(new PostCreateDto("제목", "내용", accountEntity.getName(), "12", 0), accountEntity);

        PostEntity postEntity = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        Assertions.assertThat(postEntity.getContent()).isEqualTo("내용");
//        System.out.println(create.select()
//                                .from("post")
//                                .where(post.getId().equals(1L)).fetch());
    }
}