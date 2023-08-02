package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.Tag;
import java.util.Optional;

public interface PostTagReadRepository  {
    Optional<Tag> existTag(String name);

}
