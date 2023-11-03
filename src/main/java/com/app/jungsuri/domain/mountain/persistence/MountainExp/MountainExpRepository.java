package com.app.jungsuri.domain.mountain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MountainExpRepository extends JpaRepository<MountainExpEntity, Long>, MountainReadRepository {

}
