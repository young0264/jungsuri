package com.app.jungsuri.domain.mountain.persistence;

import java.util.List;

public interface MountainReadRepository {

    List<MountainEntity> findAllMountains();

}

