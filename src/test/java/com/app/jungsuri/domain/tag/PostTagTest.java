package com.app.jungsuri.domain.tag;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.tag.persistence.Tag;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

@MockMvcTest
public class PostTagTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PostService postService;

    @Autowired
    private PostTagRepository postTagRepository;

//    @Test
//    void tag_create_성공() {
//
//        AccountEntity accountEntity = accountService.findByLoginId("12");
//        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,   new ArrayList<>(Arrays.asList("태그1", "태그2", "태그3")));
//        postService.createPost(postCreateDto, accountEntity);
//
//    }

    @Test
    void tag가_존재하는지_확인() {

        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,   new ArrayList<>(Arrays.asList("태그1", "태그2", "태그3")));
        postService.createPost(postCreateDto, accountEntity);

        Tag tag = postTagRepository.existTag("태그1").orElse(null);
        System.out.println(tag.toString());

        Assertions.assertThat(tag).isNotNull();

    }

    @Test
    void tag_count가_정상적으로_증가하는지() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,   new ArrayList<>(Arrays.asList("태그1", "태그2", "태그3")));
        postService.createPost(postCreateDto, accountEntity);

        Tag tag = postTagRepository.existTag("태그1").orElse(null);
        tag.increaseUsedCount();
        tag.increaseUsedCount();
        Assertions.assertThat(tag.getUsedCount()).isEqualTo(2);
    }
}
