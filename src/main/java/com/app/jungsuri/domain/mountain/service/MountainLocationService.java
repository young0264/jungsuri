package com.app.jungsuri.domain.mountain.service;

import com.app.jungsuri.domain.mountain.model.MountainEntity;
import com.app.jungsuri.domain.mountain.model.MountainLocationEntity;
import com.app.jungsuri.domain.mountain.repository.MountainLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class MountainLocationService { //TODO 무슨 생각으로 나눈거지

    private final MountainLocationRepository mountainLocationRepository;

    public void saveMountainLocation(String mountainAllLocation, MountainEntity mountainEntity) {
        Arrays.stream(mountainAllLocation.split(", "))
                .toList().forEach(location -> mountainLocationRepository.save(new MountainLocationEntity(location, mountainEntity)));
    }
}
