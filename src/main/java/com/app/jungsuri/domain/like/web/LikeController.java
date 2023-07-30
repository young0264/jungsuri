package com.app.jungsuri.domain.like.web;

import com.app.jungsuri.domain.like.persistence.LikeService;
import com.app.jungsuri.domain.like.web.dto.CommentLikeUpdateDto;
import com.app.jungsuri.domain.like.web.dto.LikeUpdateResultDto;
import com.app.jungsuri.domain.like.web.dto.PostLikeUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PatchMapping("/like/comment")
    public ResponseEntity modifyLike(@RequestBody CommentLikeUpdateDto commentLikeUpdateDto) {
        LikeUpdateResultDto likeUpdateResultDto = likeService.updateCommentLike(commentLikeUpdateDto.getAccountId(), commentLikeUpdateDto.getCommentId());
        return ResponseEntity.ok(likeUpdateResultDto);
    }

    @PatchMapping("/like/post")
    public ResponseEntity modifyLike(@RequestBody PostLikeUpdateDto postLikeUpdateDto) {
        LikeUpdateResultDto likeUpdateResultDto = likeService.updatePostLike(postLikeUpdateDto.getAccountId(), postLikeUpdateDto.getPostId());
        return ResponseEntity.ok(likeUpdateResultDto);
    }
}
