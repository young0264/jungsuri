package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.tag.persistence.MountainTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainTagRepository extends JpaRepository<MountainTag, Long>, MountainTagReadRepository{
}
