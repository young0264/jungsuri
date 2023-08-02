package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.event.PostCreatedEvent;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.tag.persistence.PostTag;
import com.app.jungsuri.domain.tag.persistence.Tag;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.domain.tag.persistence.repository.TagRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final EntityManager entityManager;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;//여기에 read repository가 있는데. 만지면 아노디지.
//    private final PostTagReadRepository postTagReadRepository;

    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }

    public PostEntity createPost(PostCreateDto postCreateDto, AccountEntity accountEntity) {
        PostEntity postEntity = new PostEntity(postCreateDto, accountEntity.getLoginId());
        createTags(postCreateDto.getTagList(), postEntity);
        eventPublisher.publishEvent(new PostCreatedEvent(postEntity));
        return postRepository.save(postEntity);
    }

    public void createTags(List<String> tagList, PostEntity postEntity) {
        for (String tagName : tagList) {
//            Optional<Tag> optionalTag = Optional.ofNullable(existTag(tagName));
//            Tag tag = optionalTag.orElseGet(() -> new Tag(tagName, LocalDateTime.now()));

            Tag tag = existTag(tagName);
            if (tag == null) {
                tag = new Tag(tagName, LocalDateTime.now());
            } else {
                log.info("tag name : " + tagName);
                log.info("not empty tag : " + tag.toString());
            }

            tag.increaseUsedCount();
            PostTag postTag = new PostTag(postEntity, tag);
            postTagRepository.save(postTag);
            tagRepository.save(tag);

            //TODO : refactoring
            /** 비어있을 경우, */
//            if (optionalTag.isEmpty()) {
//                tagRepository.save(tag);
//            } else {
//                log.info("tag is already exist : " + tag.getUsedCount());
//            }
//            entityManager.flush();
            /** tag usedCount만 update 할 경우, */

            log.info("tag 2: " + tag.toString());

        }
    }

    private Tag existTag(String name) {
        return postTagRepository.existTag(name).orElse(null);
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
}
