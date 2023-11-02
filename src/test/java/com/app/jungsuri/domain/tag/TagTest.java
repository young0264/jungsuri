package com.app.jungsuri.domain.tag;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.tag.persistence.TagService;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.domain.tag.persistence.repository.TagRepository;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MockMvcTest

public class TagTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostTagRepository postTagRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagRepository tagRepository;


//    @Test
//    void 이름으로_태그가_정상적으로_조회되는지() {
//        // given
//        Tag tag1 = new Tag("태그1", LocalDateTime.now());
//        Tag tag2 = new Tag("태그2", LocalDateTime.now());
//        tagRepository.save(tag1);
//        tagRepository.save(tag2);
//        // when
//        Optional<Tag> tag = tagService.findByName("태그1");
//        // then
//        Assertions.assertThat(tag.orElse(null).getName()).isEqualTo("태그1");
//    }

//    @Test
//    void 태그들이_정상적으로_생성되는지() {
//        AccountEntity accountEntity = accountService.findByLoginId("12");
//        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,  new ArrayList<>());
//        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);
//
//        tagService.createTags(Arrays.asList("태그1", "태그2", "태그3"), postEntity);
//        tagService.createTags(Arrays.asList("태그2", "태그3", "태그4"), postEntity);
//
//        Assertions.assertThat(tagRepository.findAll().size()).isEqualTo(4);
//        Assertions.assertThat(tagRepository.findByName("태그1").orElse(new Tag()).getUsedCount()).isEqualTo(1);
//        Assertions.assertThat(tagRepository.findByName("태그2").orElse(new Tag()).getUsedCount()).isEqualTo(2);
//        Assertions.assertThat(tagRepository.findByName("태그3").orElse(new Tag()).getUsedCount()).isEqualTo(2);
//        Assertions.assertThat(tagRepository.findByName("태그4").orElse(new Tag()).getUsedCount()).isEqualTo(1);
//    }

    @Test
    void 전체_태그찾기() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,  new ArrayList<>());
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);

        tagService.createPostTags(Arrays.asList("태그1", "태그2", "태그3"), postEntity);

        Assertions.assertThat(tagService.getTagList().size()).isEqualTo(3);
    }

    @Test
    void 전체_태그이름만_찾기() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostCreateDto postCreateDto = new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0,  new ArrayList<>());
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);

        tagService.createPostTags(Arrays.asList("태그1", "태그2", "태그3"), postEntity);
        List<String> tagNameList = tagService.getTagNameList();

        Assertions.assertThat(tagNameList.size()).isEqualTo(3);
        Assertions.assertThat(tagNameList.get(0)).isEqualTo("태그1");
        Assertions.assertThat(tagNameList.get(2)).isEqualTo("태그3");
    }

}
