package com.app.jungsuri.domain.mountain.repository;

import com.app.jungsuri.domain.mountain.model.MountainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MountainRepository extends JpaRepository<MountainEntity, Long>, MountainReadRepository {
}
