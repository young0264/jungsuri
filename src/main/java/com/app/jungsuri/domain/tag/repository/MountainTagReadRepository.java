package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.mountain.model.MountainEntity;

import java.util.Optional;

public interface MountainTagReadRepository {
    Optional<MountainEntity> save(MountainEntity mountainEntity);
}
