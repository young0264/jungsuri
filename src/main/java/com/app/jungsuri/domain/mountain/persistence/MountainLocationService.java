package com.app.jungsuri.domain.mountain.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class MountainLocationService {

    private final MountainLocationRepository mountainLocationRepository;

    public void saveMountainLocation(String mountainAllLocation, MountainEntity mountainEntity) {
        Arrays.stream(mountainAllLocation.split(", "))
                .toList().forEach(location -> mountainLocationRepository.save(new MountainLocationEntity(location, mountainEntity)));
    }
}
