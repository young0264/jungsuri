package com.app.jungsuri.domain.tag.persistence.repository;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;

import java.util.Optional;

public interface MountainTagReadRepository {
    Optional<MountainEntity> save(MountainEntity mountainEntity);
}