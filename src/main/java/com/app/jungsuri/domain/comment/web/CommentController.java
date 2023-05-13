package com.app.jungsuri.domain.comment.web;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.comment.web.dto.CommentDeleteDto;
import com.app.jungsuri.domain.comment.web.dto.CommentUpdateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;
    private final AccountService accountService;
    private final PostService postService;

    @PostMapping("/create")
    public String create(@ModelAttribute("commentCreateDto") CommentCreateDto commentCreateDto) {
        PostEntity postEntity = postService.getPostEntity(commentCreateDto.getPostId());
        AccountEntity accountEntity = accountService.findByLoginId(commentCreateDto.getLoginId());
        commentService.createComment(commentCreateDto.toComment(accountEntity, postEntity));
        return String.format("redirect:/post/%s/details", commentCreateDto.getPostId());
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CommentUpdateDto commentUpdateDto) {
        commentService.updateComment(commentUpdateDto.getCommentId(), commentUpdateDto.getNewComment());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody CommentDeleteDto commentDeleteDto) {
        PostEntity postEntity = postService.getPostEntity(commentDeleteDto.getPostId());
        commentService.deleteComment(commentDeleteDto.getCommentId(), postEntity);
        return ResponseEntity.ok().build();
    }
}
