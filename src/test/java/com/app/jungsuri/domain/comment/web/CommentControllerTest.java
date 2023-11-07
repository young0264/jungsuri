package com.app.jungsuri.domain.comment.web;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.service.AccountService;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.comment.persistence.CommentRepository;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.comment.web.dto.CommentDeleteDto;
import com.app.jungsuri.domain.comment.web.dto.CommentUpdateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
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
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ObjectMapper objectMapper;

    Long createdPostId;
    Long createdCommentId;

    @BeforeEach
    @WithMockUser(username = "12", password = "12")
    void init() throws Exception {

        PostCreateDto postCreateDto = new PostCreateDto("게시글 init 제목", "내용", "등록자이름", "12", 0, new ArrayList<>());

        //Account Entity , Post Entity 생성
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);
        createdPostId = postEntity.getId();

        CommentCreateDto commentCreateDto = new CommentCreateDto("등록댓글입니다.", "12", postEntity.getId());
        CommentEntity commentEntity = commentService.createComment(commentCreateDto.toComment(accountEntity, postEntity));
        createdCommentId = commentEntity.getId();

    }


    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록이_정상적으로_되는지() throws Exception {

        //given
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postRepository.findById(createdPostId).orElseThrow(() -> new IllegalArgumentException("댓글등록 테스트 : id에 해당하는 게시글이 없습니다."));

        // when (controller, service 테스트)
        mockMvc.perform(post("/comment")
                        .param("content", "댓글입니다.")
                        .param("loginId", "12")
                        .param("postId", postEntity.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/" + postEntity.getId().toString()))
                .andExpect(redirectedUrl("/post/" + postEntity.getId().toString()))
                .andExpect(authenticated());

        CommentCreateDto commentCreateDto = new CommentCreateDto("댓글 등록 테스트 댓글입니다.", "12", postEntity.getId());
        CommentEntity commentEntity = commentService.createComment(commentCreateDto.toComment(accountEntity, postEntity));

        // then
        assertThat(commentEntity.getContent()).isEqualTo("댓글 등록 테스트 댓글입니다.");
        assertThat(commentEntity.getAuthor()).isEqualTo("남의영");
    }

    //TODO
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글등록_실패케이스() throws Exception {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postRepository.findById(createdPostId).orElseThrow(() -> new IllegalArgumentException("댓글등록 테스트 : id에 해당하는 게시글이 없습니다."));
        CommentCreateDto commentCreateDto = new CommentCreateDto("짧은댓글", "12", createdPostId);

        // when
        mockMvc.perform(post("/comment")
                        .param("content", "짧음")
                        .param("loginId", "12")
                        .param("postId", createdPostId.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/" + createdPostId ))
                .andExpect(flash().attributeExists("comment_create_error"))
                .andExpect(view().name("redirect:/post/" + createdPostId ))
                .andExpect(authenticated());

        CommentEntity commentEntity = commentService.createComment(commentCreateDto.toComment(accountEntity, postEntity));

        //then
        Assertions.assertThat(commentEntity.getContent()).isEqualTo("짧은댓글");
    }


    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글수정_성공() throws Exception {

        //given
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setCommentId(createdCommentId);
        commentUpdateDto.setNewComment("수정한 댓글입니다.");

        //when
        mockMvc.perform(put("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentUpdateDto))
                        .with(csrf()))
                .andExpect(status().isOk());
        CommentEntity commentEntity = commentRepository.findById(createdCommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        //then
        assertThat(commentEntity.getContent()).isEqualTo("수정한 댓글입니다.");
    }

    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글삭제_성공() throws Exception {

        //given
        CommentDeleteDto commentDeleteDto = new CommentDeleteDto(createdCommentId, createdPostId);

        //when
        mockMvc.perform(delete("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDeleteDto))
                .with(csrf())
        ).andExpect(status().isOk());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.getComment(1L);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo("해당 댓글이 없습니다.");
    }

}