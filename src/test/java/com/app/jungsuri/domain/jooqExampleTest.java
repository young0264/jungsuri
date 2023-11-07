package com.app.jungsuri.domain;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.ArrayList;

@MockMvcTest
public class jooqExampleTest {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;


    @Test
    @WithMockUser(username="12", password="12")
    public void test() {
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(RuntimeException::new);
        PostEntity postEntity = postService.createPost(new PostCreateDto("제목", "내용", accountEntity.getName(), "12", 0, new ArrayList<>()), accountEntity);

        /**
         * jooq로 조회
         */
        Assertions.assertThat(postService.findPostEntityById(postEntity.getId()).getContent()).isEqualTo("내용");

        /**
         * jpa로 조회
         */
        Assertions.assertThat(postRepository.findById(postEntity.getId()).orElseThrow(RuntimeException::new).getContent()).isEqualTo("내용");

        Assertions.assertThat(postEntity.getContent()).isEqualTo("내용");
    }
}