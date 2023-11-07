package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.tag.model.Tag;
import java.util.List;
import java.util.Optional;

public interface TagReadRepository {

    Optional<Tag> findByName(String name);

    List<String> findAllPostTags();
}
