package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.tag.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostTagRepository extends JpaRepository<PostTag, Long>, PostTagReadRepository{

}
