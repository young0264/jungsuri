package com.app.jungsuri.domain.mountain.persistence.MountainExp;

import java.util.Optional;

public interface MountainExpReadRepository {

    Optional<Integer> get100MountainExp(Long accountId);
}
