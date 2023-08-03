package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagReadRepository{

}
