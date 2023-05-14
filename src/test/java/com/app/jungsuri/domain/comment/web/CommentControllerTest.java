package com.app.jungsuri.domain.comment.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.comment.persistence.CommentRepository;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.comment.web.dto.CommentDeleteDto;
import com.app.jungsuri.domain.comment.web.dto.CommentUpdateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    @WithMockUser(username = "12", password = "12")
    void init() throws Exception {
        // given
        PostCreateDto postCreateDto = new PostCreateDto("제목", "내용", "등록자이름", "12", 0);

        //Account Entity , Post Entity 생성
        AccountEntity accountEntity = accountService.findByLoginId("12");
        postService.createPost(postCreateDto, accountEntity);

    }


    //TODO 동시실행 문제 해결
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록이_정상적으로_되는지() throws Exception {


        // when
        mockMvc.perform(post("/comment/create")
                        .param("content", "댓글입니다.")
                        .param("loginId", "12")
                        .param("postId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/1/details"))
                .andExpect(redirectedUrl("/post/1/details"))
                .andExpect(authenticated());

        // then
        CommentEntity commentEntity = commentRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        assertThat(commentEntity.getContent()).isEqualTo("댓글입니다.");
        assertThat(commentEntity.getAuthor()).isEqualTo("남의영");
    }

    //TODO
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록_실패케이스() throws Exception {

        // when
        mockMvc.perform(post("/comment/create")
                        .param("content", "")
                        .param("loginId", "12")
                        .param("postId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/1/details"))
                .andExpect(authenticated());
        //then
    }


    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글수정_성공() throws Exception {

        CommentUpdateDto dto = new CommentUpdateDto();
        dto.setCommentId(1L);
        dto.setNewComment("수정한 댓글입니다.");
        PostEntity postEntity = postService.getPostEntity(1L);
        CommentEntity comment = commentService.createComment(new Comment(1L, "댓글입니다.", "12", 0, LocalDateTime.now(), LocalDateTime.now(), postEntity, null));
        mockMvc.perform(put("/comment/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk());
        CommentEntity commentEntity = commentRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        assertThat(commentEntity.getContent()).isEqualTo("수정한 댓글입니다.");
    }

    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글삭제_성공() throws Exception {

        //given
        CommentDeleteDto dto = new CommentDeleteDto();
        dto.setCommentId(1L);
        dto.setPostId(1L);

        //when
        mockMvc.perform(delete("/comment/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf())
                ).andExpect(status().isOk());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.getComment(1L);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo("해당 댓글이 없습니다.");

    }


}