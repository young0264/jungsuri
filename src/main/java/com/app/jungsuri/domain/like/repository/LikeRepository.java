package com.app.jungsuri.domain.like.repository;

import com.app.jungsuri.domain.like.model.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeReadRepository, LikeWriteRepository{

}
