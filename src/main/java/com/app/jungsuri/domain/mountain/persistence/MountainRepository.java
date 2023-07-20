package com.app.jungsuri.domain.mountain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MountainRepository extends JpaRepository<MountainEntity, Long>, MountainReadRepository {
}
