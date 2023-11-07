package com.app.jungsuri.domain.comment.repository;

import com.app.jungsuri.domain.comment.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
