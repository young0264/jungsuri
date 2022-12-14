package com.jungsuri.jungsuri.controller;

import com.jungsuri.jungsuri.domain.member.Member;
import com.jungsuri.jungsuri.domain.post.Post;
import com.jungsuri.jungsuri.domain.post.PostRepository;
import com.jungsuri.jungsuri.dto.post.PostDto;
import com.jungsuri.jungsuri.service.MemberService;
import com.jungsuri.jungsuri.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PrinterInfo;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/post/new")
    public String createPost(@ModelAttribute("postDto") PostDto postDto) {
        return "post/newPost";
    }

    @PostMapping("/post/new")
    public String createPost(@Valid PostDto postDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "post/newPost";
        }
        Member member = memberService.findMemberByusername(principal.getName());
        postService.createPost(postDto, member);

        return "redirect:/post/list";
    }

    @GetMapping("post/modify/{id}")
//    public String postModify(PostDto postDto, @PathVariable("id")) {
    public String postModify(@ModelAttribute("postDto") PostDto postDto, @PathVariable("id") Long id,
                             Principal principal) {
        Post post = postService.findPostById(id);
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setMember(post.getMember());
        log.info("1111 " + id + " 2222 " + postDto.getContent());
        return "post/post-modify";
    }

    @PostMapping("post/modify/{id}")
    public String postModify(@Valid PostDto postDto, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            return "post/postList";
//        }
        log.info("postdto. context" + postDto.getContent());
        Post post = postService.findPostById(id);
        log.info("nick name1 : " + post.getMember().getUsername());
        log.info("nick name2 : " + post.getMember().getId());
        postService.modifyPost(post, postDto);

        return "redirect:/post/list";
    }


    @GetMapping("post/list")
    public String showPostList(Model model) {

        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList", postList);
        return "post/postList";
    }

    @DeleteMapping("post/delete/{id}")
    public String postDelete(Principal principal, @PathVariable Long id) {
        Post post = postService.findPostById(id);
        postService.deletePost(post);
        return "redirect:/post/list";
    }

    @GetMapping("post/detail/{id}")
    public String postDetail(@ModelAttribute("postDto") PostDto postDto, @PathVariable("id") Long id) {
        Post post = postService.findPostById(id);
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setId(post.getId());
        postDto.setMember(post.getMember());
        return "post/post-detail";
    }

}



