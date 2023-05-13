package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }

    public PostEntity createPost(PostCreateDto postCreateDto, AccountEntity accountEntity) {
        PostEntity postEntity = postCreateDto.toPost(accountEntity.getLoginId()).toEntity();
        return postRepository.save(postEntity);
    }

    public PostEntity getPostEntity(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
    }


    public void updatePost(PostEntity postEntityExist, Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        postEntity.update(postEntityExist);
    }

    public void deletePost(Long postId) {
        postRepository.delete(getPostEntity(postId));
    }
}
