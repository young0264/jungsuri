package com.app.jungsuri.domain.mountain.persistence;

import com.app.jungsuri.domain.mountain.service.MountainService;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

@MockMvcTest
class MountainServiceTest {
    @Autowired
    MountainService mountainService;

    @Test
    @WithMockUser(username = "12" , password = "12")
    void convertMountain100ExpToMountain100ExpByPer() {
        
        Integer mountain100ExpByPer = mountainService.convertMountain100ExpToMountain100ExpByPer(9800);
        Assertions.assertThat(mountain100ExpByPer).isEqualTo(10);
    }
}