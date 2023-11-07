package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.tag.model.MountainTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainTagRepository extends JpaRepository<MountainTag, Long>, MountainTagReadRepository{
}
