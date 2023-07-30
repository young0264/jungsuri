package com.app.jungsuri.domain.like.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeReadRepository {
}
