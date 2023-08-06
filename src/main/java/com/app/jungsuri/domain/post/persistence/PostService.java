package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.event.PostCreatedEvent;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }

    public PostEntity createPost(PostCreateDto postCreateDto, AccountEntity accountEntity) {
        PostEntity postEntity = new PostEntity(postCreateDto, accountEntity.getLoginId());
        eventPublisher.publishEvent(new PostCreatedEvent(postEntity));
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

    @Transactional(readOnly = true)
    public PostEntity findPostEntityById(Long id) {
        return postRepository.findPostEntityById(id);
    }

    public List<PostEntity> getPostListByTags(List<String> searchTags) {
        return postRepository.findAllByTags(searchTags);
    }
}
