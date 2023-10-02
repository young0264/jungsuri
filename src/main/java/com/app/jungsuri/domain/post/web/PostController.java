package com.app.jungsuri.domain.post.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final AccountService accountService;

    // 게시글 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<PostEntity> postList = postService.getPostList();
        model.addAttribute("postList", postList);
        return "post/list";
    }

    // 게시글 등록 화면 이동
    @GetMapping("")
    public String write(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        model.addAttribute("mode", "create");
        return "post/form";
    }

    // 게시글 등록 처리
    @PostMapping("")
    public String write(@ModelAttribute("postCreateDto") PostCreateDto postCreateDto, Principal principal, RedirectAttributes redirectAttributes){
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        postService.createPost(postCreateDto, accountEntity);
        redirectAttributes.addFlashAttribute("message", "게시물이 성공적으로 등록되었습니다.");
        return "redirect:/post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/{postId}/details")
    public String view(@PathVariable Long postId, Model model, Principal principal) {
        PostEntity postEntity = postService.getPostEntity(postId);
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());

        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("postEntity", postEntity);
        model.addAttribute("commentCreateDto", new CommentCreateDto());
        model.addAttribute("commentList", postEntity.getCommentList());
        return "post/details";
    }

    // 게시글 수정 화면 이동
    @GetMapping("/{postId}/updateView")
    public String update(@PathVariable Long postId, Model model) {
        PostEntity postEntity = postService.getPostEntity(postId);
        model.addAttribute("mode", "update");
        model.addAttribute("postEntity", postEntity);
        return "post/form";
    }

    // 게시글 수정 처리
    @PatchMapping("/{postId}")
    public String edit(@PathVariable Long postId, @ModelAttribute("postEntity") PostEntity existingPost, RedirectAttributes redirectAttributes) {
        postService.updatePost(existingPost, postId);
        redirectAttributes.addFlashAttribute("message", "/titan/live/assets/");
        return "redirect:/post/list";
    }

    // 게시글 삭제 처리
    @DeleteMapping("/{postId}")
    public String delete(@PathVariable Long postId, RedirectAttributes redirectAttributes){
        postService.deletePost(postId);
        System.out.println("post delete complete");
        redirectAttributes.addFlashAttribute("delete_message", "게시물이 성공적으로 삭제되었습니다.");
        return "redirect:/post/list";
    }
}
