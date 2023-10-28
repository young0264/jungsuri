package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.domain.tag.persistence.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    public void deleteTag(Long postId) {
        postTagRepository.deleteByPostEntity(postId);
    }

    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    public void createTags(List<String> tagList, PostEntity postEntity) {
        for (String tagName : tagList) {
            createTag(postEntity, tagName);
        }
    }

    private void createTag(PostEntity postEntity, String tagName) {
        /** 태그가 있으면 used += 1, 태그가 없으면 태그를 생성후 used += 1 */
        Optional<Tag> optionalTag = findByName(tagName);
        optionalTag.ifPresentOrElse(existTag -> {
            existTag.increaseUsedCount();
            tagRepository.save(existTag);
            postTagRepository.save(new PostTag(postEntity, tagName));
        }, () -> {
            postTagRepository.save(new PostTag(postEntity,tagName));
        });
    }

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }

    public List<String> getTagNameList() {
        return tagRepository.findAllTagsName();
    }
}
