package com.app.jungsuri.domain.post.web;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;


    @Test
    @WithMockUser(username = "12", password = "12")
    void post등록_viewpage가_보이는지() throws Exception {

        mockMvc.perform(get("/post/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/write"))
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
    }

    @Test
    @WithMockUser(username="12", password="12")
    void post수정_성공() throws Exception {
        PostEntity postEntity = postService.cretePost(new PostCreateDto("수정전제목", "수정전내용", ""), "12");
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