package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import com.app.jungsuri.domain.tag.persistence.MountainTag;
import com.app.jungsuri.domain.tag.persistence.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MountainTagRepository{
    Optional<MountainEntity> save(MountainEntity mountainEntity);
}
