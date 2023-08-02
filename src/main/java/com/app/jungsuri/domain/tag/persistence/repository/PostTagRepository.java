package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long>, PostTagReadRepository{
}
