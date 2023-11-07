package com.app.jungsuri.domain.like.controller;

import com.app.jungsuri.domain.like.service.LikeService;
import com.app.jungsuri.domain.like.dto.CommentLikeDto;
import com.app.jungsuri.domain.like.dto.CommentLikeUpdateDto;
import com.app.jungsuri.domain.like.dto.LikeUpdateResultDto;
import com.app.jungsuri.domain.like.dto.PostLikeUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "좋아요 관련 API", description = "좋아요(like) 관련 API")
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like/post")
    @Operation(summary = "게시글 좋아요 확인", description = "게시글에 좋아요가 눌려져있는지 확인합니다.")
    public ResponseEntity getPostLike(@RequestParam Long accountId, @RequestParam Long postId) {
        boolean checkedPostLike = likeService.isCheckedPostLike(accountId, postId);
        return ResponseEntity.ok(checkedPostLike);
    }

    @GetMapping("/like/comment")
    @Operation(summary = "댓글 좋아요 확인", description = "댓글에 좋아요가 눌려져있는지 확인합니다.")
    public ResponseEntity getCommentLike(CommentLikeDto commentLikeDto) {
        List<Long> commentLikeList = likeService.getCommentLikeList(commentLikeDto.getAccountId(), commentLikeDto.getPostId());
        return ResponseEntity.ok(commentLikeList);
    }

    @PatchMapping("/like/comment")
    public ResponseEntity modifyLike(@RequestBody CommentLikeUpdateDto commentLikeUpdateDto) {
        LikeUpdateResultDto likeUpdateResultDto = likeService.updateCommentLike(commentLikeUpdateDto);
        return ResponseEntity.ok(likeUpdateResultDto);
    }

    @PatchMapping("/like/post")
    public ResponseEntity modifyLike(@RequestBody PostLikeUpdateDto postLikeUpdateDto) {
        LikeUpdateResultDto likeUpdateResultDto = likeService.updatePostLike(postLikeUpdateDto.getAccountId(), postLikeUpdateDto.getPostId());
        return ResponseEntity.ok(likeUpdateResultDto);
    }
}
