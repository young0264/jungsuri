package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.tag.persistence.repository.PostTagRepository;
import com.app.jungsuri.domain.tag.persistence.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    public void createTags(List<String> tagList, PostEntity postEntity) {
        for (String tagName : tagList) {
            Tag tag = saveTag(tagName);
            PostTag postTag = new PostTag(postEntity, tag);
            postTagRepository.save(postTag);
        }
    }

    private Tag saveTag(String tagName) {
        Optional<Tag> optionalTag = tagRepository.findByName(tagName);
        Tag tag = optionalTag.orElse(null);
        if (tag == null) {
            tag = new Tag(tagName, LocalDateTime.now());
            tag.increaseUsedCount();
        } else {
            tag.increaseUsedCount();
        }
        tagRepository.save(tag);
        return tag;
    }
}
