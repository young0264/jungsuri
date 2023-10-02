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

    /**
     * 인기 게시글(top5) post entity 가져오기
     */
    public List<PostEntity> getPostListByTop5() {
        return postRepository.findTop5ByLikeCountAsc();
    }

    /** 최신 update date 순으로 모든 post list 가져오기 */
    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }


    /** 게시글 생성 */
    public PostEntity createPost(PostCreateDto postCreateDto, AccountEntity accountEntity) {
        PostEntity postEntity = new PostEntity(postCreateDto, accountEntity.getLoginId());
        eventPublisher.publishEvent(new PostCreatedEvent(postEntity));
        return postRepository.save(postEntity);
    }

    /** post (pk) id로 post entity 가져오기 by JPA*/
    public PostEntity getPostEntity(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
    }

    /** post 게시물 수정  */
    public void updatePost(PostEntity postEntityExist, Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        postEntity.update(postEntityExist);
    }

    /** post 게시물 삭제 */
    public void deletePost(Long postId) {
        postRepository.delete(getPostEntity(postId));
    }

    /** post (pk) id로 post entity 가져오기 by JOOQ */
    @Transactional(readOnly = true)
    public PostEntity findPostEntityById(Long id) {
        return postRepository.findPostEntityById(id);
    }

    /** tags(태그들)에 해당하는 게시글 가져오기 */
    public List<PostEntity> getPostListByTags(List<String> searchTags) {
        return postRepository.findAllByTags(searchTags);
    }
}
