package com.app.jungsuri.domain.mountain.persistence;

import java.util.List;

public interface MountainReadRepository {

    List<MountainEntity> findAllMountains();

    List<MountainEntity> findMountainListByPagination(int currentPageNumber);

    List<String> findAllMountainsName();

    int findMountainHeightByName(String name);

    int getMountainCount();

}

