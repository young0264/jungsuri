package com.app.jungsuri.domain.mountain.repository;

import com.app.jungsuri.domain.mountain.model.MountainExpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MountainExpRepository extends JpaRepository<MountainExpEntity, Long>, MountainExpReadRepository {

}
