package com.app.jungsuri.domain.comment.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class CommentControllerTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    AccountService accountService;

    //TODO 동시실행 문제 해결
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록이_정상적으로_되는지() throws Exception {

        // given
        PostCreateDto postCreateDto = new PostCreateDto("제목", "내용","등록자이름", "12", 0);

        //Account Entity , Post Entity 생성
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);

        CommentCreateDto commentCreateDto = new CommentCreateDto("댓글입니다", postEntity, accountEntity,0);

        //comment Entity 생성
        CommentEntity comment = commentService.createComment(commentCreateDto.toComment(accountEntity), postEntity);

        // when
        mockMvc.perform(post("/comment/create")
                        .param("content", "댓글입니다.")
                        .param("postId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/1/details"))
                .andExpect(redirectedUrl("/post/1/details"))
                .andExpect(authenticated());

        // then
        Assertions.assertThat(comment.getContent()).isEqualTo("댓글입니다");
        Assertions.assertThat(comment.getPostEntity().getAuthor()).isEqualTo("등록자이름");
    }

    //TODO
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록_실패케이스() throws Exception {

        // given
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostCreateDto postCreateDto = new PostCreateDto("제목", "내용","등록자이름", "12", 0);
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);
        CommentCreateDto commentCreateDto = new CommentCreateDto("", postEntity, accountEntity,0);
        CommentEntity commentEntity = commentService.createComment(commentCreateDto.toComment(accountEntity), postEntity);

        // when

        //then
        mockMvc.perform(post("/comment/create")
                        .param("content", "")
                        .param("postId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/1/details"))
                .andExpect(authenticated());
    }




}