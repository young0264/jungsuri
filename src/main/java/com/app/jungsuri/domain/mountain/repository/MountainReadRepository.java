package com.app.jungsuri.domain.mountain.repository;

import com.app.jungsuri.domain.mountain.model.MountainEntity;

import java.util.List;

public interface MountainReadRepository {

    List<MountainEntity> findAllMountains();

    List<MountainEntity> findMountainListByPagination(int currentPageNumber);

    List<String> findAllMountainsName();

    int findMountainHeightByName(String name);

    int getMountainCount();

    MountainEntity findByName(String name);

}

