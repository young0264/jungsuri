package com.app.jungsuri.domain.comment.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
class CommentControllerTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentService commentService;


    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록이_정상적으로_되는지() throws Exception {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setLoginId("12");
        accountEntity.setPassword("12");
        accountEntity.setName("내이름");
        PostEntity postEntity = new PostCreateDto().toPost("12", "내이름").toEntity();
        CommentCreateDto commentCreateDto = new CommentCreateDto();
        commentCreateDto.setContent("댓글입니다.");
        commentCreateDto.setPostEntity(postEntity);
        commentCreateDto.setAccountEntity(accountEntity);

        // when
        commentService.createComment(commentCreateDto.toComment(accountEntity), postEntity);

        // then

        mockMvc.perform(post("/comment/create")
                .param("content", "댓글입니다.")
                .param("postId", "1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/1/details"))
                .andExpect(authenticated());
    }

}