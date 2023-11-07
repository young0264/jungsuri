package com.app.jungsuri.domain.post.repository;

import com.app.jungsuri.domain.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<PostEntity, Long>, PostReadRepository {

    List<PostEntity> findAllByOrderByUpdatedAtDesc();
}
