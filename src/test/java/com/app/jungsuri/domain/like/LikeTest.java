package com.app.jungsuri.domain.like;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.service.AccountService;
import com.app.jungsuri.domain.comment.model.CommentEntity;
import com.app.jungsuri.domain.comment.service.CommentService;
import com.app.jungsuri.domain.comment.dto.CommentCreateDto;
import com.app.jungsuri.domain.like.persistence.LikeService;
import com.app.jungsuri.domain.like.web.dto.CommentLikeUpdateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.ArrayList;

@MockMvcTest
public class LikeTest {

    @Autowired
    AccountService accountService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;


    Long createdPostId;
    Long createdCommentId;

    @BeforeEach
    @WithMockUser(username = "12", password = "12")
    void init() throws Exception {

        PostCreateDto postCreateDto = new PostCreateDto("게시글 init 제목", "내용", "등록자이름", "12", 0, new ArrayList<>());

        /** Account Entity , Post Entity, Comment Entity 생성 */
        AccountEntity accountEntity = accountService.findByLoginId("12");
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);

        CommentCreateDto commentCreateDto = new CommentCreateDto("등록댓글입니다.", "12", postEntity.getId());
        CommentEntity commentEntity = commentService.createComment(commentCreateDto.toComment(accountEntity, postEntity));

        createdPostId = postEntity.getId();
        createdCommentId = commentEntity.getId();

    }
    @Test
    @WithMockUser(username = "12", password = "12")
    void 게시글_좋아요_create_Test() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        likeService.updatePostLike(accountEntity.getId(), createdPostId);

        Assertions.assertThat(likeService.isCheckedPostLike(accountEntity.getId(),createdPostId)).isTrue();
    }
    @Test
    @WithMockUser(username = "12", password = "12")
    void 게시글_좋아요_delete_Test() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        likeService.updatePostLike(accountEntity.getId(), createdPostId);
        likeService.updatePostLike(accountEntity.getId(), createdPostId);

        Assertions.assertThat(likeService.isCheckedPostLike(accountEntity.getId(),createdPostId)).isFalse();
    }
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글_좋아요_create_Test() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        likeService.updateCommentLike(new CommentLikeUpdateDto(accountEntity.getId(), createdPostId, createdCommentId));

        Assertions.assertThat(likeService.isCheckedCommentLike(accountEntity.getId(), createdCommentId)).isTrue();
    }
    @Test
    @WithMockUser(username = "12", password = "12")
    void 댓글_좋아요_delete_Test() {
        AccountEntity accountEntity = accountService.findByLoginId("12");
        likeService.updateCommentLike(new CommentLikeUpdateDto(accountEntity.getId(), createdPostId, createdCommentId));
        likeService.updateCommentLike(new CommentLikeUpdateDto(accountEntity.getId(), createdPostId, createdCommentId));

        Assertions.assertThat(likeService.isCheckedPostLike(accountEntity.getId(),createdCommentId)).isFalse();
    }



}