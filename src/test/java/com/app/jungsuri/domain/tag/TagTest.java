package com.app.jungsuri.domain.tag;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.tag.persistence.Tag;
import com.app.jungsuri.domain.tag.persistence.TagService;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.domain.tag.persistence.repository.TagRepository;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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


    @Test
    void 이름으로_태그가_정상적으로_조회되는지() {
        // given
        Tag tag1 = new Tag("태그1", LocalDateTime.now());
        Tag tag2 = new Tag("태그2", LocalDateTime.now());
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        // when
        Optional<Tag> tag = tagService.findByName("태그1");
        // then
        Assertions.assertThat(tag.orElse(null).getName()).isEqualTo("태그1");
    }

}
