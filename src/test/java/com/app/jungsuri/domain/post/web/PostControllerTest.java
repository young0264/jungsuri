package com.app.jungsuri.domain.post.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostReadRepositoryImpl;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
//@JooqTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostReadRepositoryImpl postReadRepository;

    @Autowired
    private AccountService accountService;


    @Test
    @WithMockUser(username = "12", password = "12")
    void post등록_viewpage가_보이는지() throws Exception {

        mockMvc.perform(get("/post/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/form"))
                .andExpect(model().attributeExists("postCreateDto"))
                .andExpect(authenticated());
    }

    @Test
    @WithMockUser(username="12", password="12")
    void post등록_성공() throws Exception {

        mockMvc.perform(post("/post/create")
                        .param("title", "제목222")
                        .param("content", "내용222"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/list"))
                .andExpect(authenticated());

        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postService.createPost(new PostCreateDto("게시글 등록 제목", "게시글 등록 내용", "게시글 등록 이름", null, 0), accountEntity);

        Assertions.assertThat(postEntity.getTitle()).isEqualTo("게시글 등록 제목");
        Assertions.assertThat(postRepository.findPostEntityById(postEntity.getId()).getContent()).isEqualTo("게시글 등록 내용");
    }

    @Test
    @WithMockUser(username="12", password="12")
    void post수정_성공() throws Exception {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postService.createPost(new PostCreateDto("수정전제목", "수정전내용", "수정전이름", null, 0), accountEntity);
        mockMvc.perform(patch("/post/" + postEntity.getId() +"/update")
                        .param("title", "수정제목2")
                        .param("content", "수정내용2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/list"))
                .andExpect(authenticated());
        Assertions.assertThat(postEntity.getTitle()).isEqualTo("수정제목2");
        Assertions.assertThat(postEntity.getContent()).isEqualTo("수정내용2");
    }

}