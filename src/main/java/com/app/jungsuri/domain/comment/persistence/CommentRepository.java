package com.app.jungsuri.domain.comment.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
