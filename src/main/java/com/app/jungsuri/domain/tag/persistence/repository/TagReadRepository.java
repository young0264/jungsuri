package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.Tag;
import java.util.List;
import java.util.Optional;

public interface TagReadRepository {

    Optional<Tag> findByName(String name);

    List<String> findAllTagsName();
}
