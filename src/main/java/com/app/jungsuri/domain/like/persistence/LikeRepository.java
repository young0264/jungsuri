package com.app.jungsuri.domain.like.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeReadRepository, LikeWriteRepository{

}
