package com.app.jungsuri.domain.mountain.repository;

import java.util.Optional;

public interface MountainExpReadRepository {

    Optional<Integer> get100MountainExp(Long accountId);
}
