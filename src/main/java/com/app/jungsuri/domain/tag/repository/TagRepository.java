package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagReadRepository {

}
