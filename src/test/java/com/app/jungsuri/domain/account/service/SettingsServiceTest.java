package com.app.jungsuri.domain.account.service;

import com.app.jungsuri.domain.account.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@MockMvcTest
public class SettingsServiceTest {
    Integer GARI_MOUNTAIN_HEIGHT = 1050;

    @Autowired
    private SettingsService settingsService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void 유저_등산경험치_부여_테스트() throws Exception {
        List<String> LoginIdArr = List.of("12");
        settingsService.updateMountainExp(new MountainExpUpdateDto("가리산", LoginIdArr, "2023-12-12"));
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(() -> new Exception("찾는 아이디가 없습니다."));
        Assertions.assertThat(accountEntity.getMountainExp()).isEqualTo(GARI_MOUNTAIN_HEIGHT);
    }

}
