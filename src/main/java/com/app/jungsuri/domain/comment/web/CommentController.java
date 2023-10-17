package com.app.jungsuri.domain.comment.web;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.comment.web.dto.CommentDeleteDto;
import com.app.jungsuri.domain.comment.web.dto.CommentUpdateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "댓글 API", description = "swagger 댓글 API")
public class CommentController {


    private final CommentService commentService;
    private final AccountService accountService;
    private final PostService postService;

    @PostMapping("")
    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
    public String create(@Valid @ModelAttribute("commentCreateDto") CommentCreateDto commentCreateDto,
                         BindingResult errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            List<ObjectError> allErrors = errors.getAllErrors();
            allErrors.stream().forEach(objectError -> {
                redirectAttributes.addFlashAttribute("comment_create_error", objectError.getDefaultMessage());
            });
            return String.format("redirect:/post/%s/details", commentCreateDto.getPostId());
        }
        PostEntity postEntity = postService.getPostEntity(commentCreateDto.getPostId());
        AccountEntity accountEntity = accountService.findByLoginId(commentCreateDto.getLoginId());
        Comment comment = commentCreateDto.toComment(accountEntity, postEntity);
        commentService.createComment(comment);

        return String.format("redirect:/post/%s/details", commentCreateDto.getPostId());
    }

    @PutMapping("")
    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    public ResponseEntity update(@RequestBody CommentUpdateDto commentUpdateDto) {
        commentService.updateComment(commentUpdateDto.getCommentId(), commentUpdateDto.getNewComment());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    public ResponseEntity delete(@RequestBody CommentDeleteDto commentDeleteDto) {
        PostEntity postEntity = postService.getPostEntity(commentDeleteDto.getPostId());
        commentService.deleteComment(commentDeleteDto.getCommentId(), postEntity);
        return ResponseEntity.ok().build();
    }
}
