package com.app.jungsuri.domain.like.web;

import com.app.jungsuri.domain.like.persistence.LikeService;
import com.app.jungsuri.domain.like.web.dto.CommentLikeDto;
import com.app.jungsuri.domain.like.web.dto.CommentLikeUpdateDto;
import com.app.jungsuri.domain.like.web.dto.LikeUpdateResultDto;
import com.app.jungsuri.domain.like.web.dto.PostLikeUpdateDto;
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
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like/post")
    public ResponseEntity getPostLike(@RequestParam Long accountId, @RequestParam Long postId) {
        boolean checkedPostLike = likeService.isCheckedPostLike(accountId, postId);
        return ResponseEntity.ok(checkedPostLike);
    }

    @GetMapping("/like/comment")
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
