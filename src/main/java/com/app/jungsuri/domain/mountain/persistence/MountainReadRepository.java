package com.app.jungsuri.domain.mountain.persistence;

import java.util.List;

public interface MountainReadRepository {

    List<MountainEntity> findAllMountains();

    List<String> findAllMountainsName();

    int findMountainHeightByName(String name);
}

