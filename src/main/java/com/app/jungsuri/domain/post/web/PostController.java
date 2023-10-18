package com.app.jungsuri.domain.post.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.comment.persistence.CommentService;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.web.dto.PostSearchDto;
import com.app.jungsuri.domain.tag.persistence.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "게시글 API", description = "swagger 게시글 API")
public class PostController {

    private final TagService tagService;
    private final PostService postService;
    private final AccountService accountService;
    private final CommentService commentService;


    /** 게시글 등록 처리 */
    @PostMapping("")
    @Operation(summary = "게시글 등록", description = "게시글 작성")
    public String write(@ModelAttribute("postCreateDto") PostCreateDto postCreateDto, Principal principal, RedirectAttributes redirectAttributes){
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        PostEntity postEntity = postService.createPost(postCreateDto, accountEntity);
        tagService.createTags(postCreateDto.getTagList(), postEntity);

        redirectAttributes.addFlashAttribute("message", "게시물이 성공적으로 등록되었습니다.");
        return "redirect:/post/list";
    }

    /** 게시글 등록 화면 이동 */
    @GetMapping("")
    @Operation(summary = "게시글 등록 페이지 조회", description = "게시글 작성 페이지를 조회합니다.")
    public String write(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        model.addAttribute("mode", "create");
        return "post/form";
    }

    /** 게시글 수정 처리 */
    @PatchMapping("/{postId}")
    @Operation(summary="게시글 수정 처리", description="게시글 수정 처리")
    public String edit(@PathVariable Long postId, @ModelAttribute("postEntity") PostEntity existingPost, RedirectAttributes redirectAttributes) {
        postService.updatePost(existingPost, postId);
        redirectAttributes.addFlashAttribute("message", "/titan/live/assets/");
        return "redirect:/post/list";
    }

    /** 게시글 삭제 처리 */
    @DeleteMapping("/{postId}")
    @Operation(summary="게시글 삭제 처리", description="게시글 삭제 처리")
    public String delete(@PathVariable Long postId, RedirectAttributes redirectAttributes){
        tagService.deleteTag(postId);
        postService.deletePost(postId);
        System.out.println("post delete complete");
        redirectAttributes.addFlashAttribute("delete_message", "게시물이 성공적으로 삭제되었습니다.");
        return "redirect:/post/list";
    }

    /** 게시글 상세 페이지 */
    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 페이지 조회", description = "하나의 게시글을 조회할 수 있는 페이지로 이동합니다.")
    public String view(@PathVariable Long postId, Model model, Principal principal) {
        PostEntity postEntity = postService.getPostEntity(postId);
        List<PostEntity> postListByTop5 = postService.getTop5ListByLikeCount();
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        List<CommentEntity> recentCommentListTop5 = commentService.getRecentCommentList();

        model.addAttribute("postListByTop5", postListByTop5);
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("tagList", tagService.getTagNameList());
        model.addAttribute("postEntity", postEntity);
        model.addAttribute("commentCreateDto", new CommentCreateDto());
        model.addAttribute("commentList", postEntity.getCommentList());
        model.addAttribute("recentCommentListTop5", recentCommentListTop5);

        return "post/details";
    }

    /** 게시글 수정 화면 이동 */
    @GetMapping("/{postId}/updateView")
    @Operation(summary = "게시글 수정 페이지 조회", description = "게시글 수정 페이지를 조회합니다.")
    public String update(@PathVariable Long postId, Model model) {
        PostEntity postEntity = postService.getPostEntity(postId);
        model.addAttribute("mode", "update");
        model.addAttribute("postEntity", postEntity);
        return "post/form";
    }

    /** 게시글 태그 (or 조건) 검색 */
    @PostMapping("/list")
    @Operation(summary = "게시글 태그 검색", description = "searchTags 정보에 따라 태그가 달린 게시글을 조회합니다.")
    public String search(@RequestBody PostSearchDto postSearchDto, Model model) {

        List<PostEntity> postListByTags = postService.getPostListByTags(postSearchDto.getSearchTags());
        model.addAttribute("postList", postListByTags);
        return "post/list :: #post_list ";
    }


    /** 게시글 목록 조회 */
    @GetMapping("/list")
    @Operation(summary = "게시글 조회", description = "페이지 네이션이 적용된 게시글을 조회합니다.")
    public String list(@RequestParam(required = false, defaultValue = "1") int currentPageNumber , Model model) {

        List<PostEntity> postListByPagination = postService.getPostListByPagination(currentPageNumber);
        List<PostEntity> postListByTop5 = postService.getTop5ListByLikeCount();
        List<CommentEntity> recentCommentListTop5 = commentService.getRecentCommentList();

        model.addAttribute("beforePageNum", postService.getBeforePageNum(currentPageNumber));
        model.addAttribute("nextPageNum", postService.getEndPageNum(currentPageNumber));
        model.addAttribute("pagingCount", postService.getPagingNumber());
        model.addAttribute("postList", postListByPagination);
        model.addAttribute("tagList", tagService.getTagNameList());
        model.addAttribute("postListByTop5", postListByTop5);
        model.addAttribute("recentCommentListTop5", recentCommentListTop5);
        return "post/list";
    }


}
