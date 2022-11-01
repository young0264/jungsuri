package com.jungsuri.jungsuri.service;

import com.jungsuri.jungsuri.domain.member.Member;
import com.jungsuri.jungsuri.domain.post.Post;
import com.jungsuri.jungsuri.domain.post.PostRepository;
import com.jungsuri.jungsuri.dto.post.PostDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    //게시글 작성
    public Post createPost(PostDto postDto, Member member) {
//        LocalDateTime timenow = LocalDateTime.now();
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createDate(LocalDateTime.now())
                .postTime(postDto.getPostTime())
                .member(member)
                .build();
        log.info("time" + post.getCreateDate());
        return postRepository.save(post);
    }

    public Post modifyPost(Post post, PostDto postDto) {
        post.modifyPost(postDto.getTitle(), postDto.getContent(), postDto.getMember());
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Post findPostById(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        return byId.get();
    }

}

