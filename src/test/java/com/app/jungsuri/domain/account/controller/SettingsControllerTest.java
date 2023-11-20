package com.app.jungsuri.domain.account.controller;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.account.service.SettingsService;
import com.app.jungsuri.domain.account.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.dto.PasswordUpdateDto;
import com.app.jungsuri.domain.mountain.repository.MountainRepository;
import com.app.jungsuri.infra.MockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class SettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SettingsService settingsService;
    @Autowired
    private MountainRepository mountainRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @WithMockUser(username = "12" , password = "12")
    void settings_페이지이동() throws Exception {
        mockMvc.perform(get("/settings/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("accountEntity"))
                .andExpect(model().attributeExists("passwordUpdateDto"))
                .andExpect(view().name("account/profile"));
    }

    @Test
    @WithMockUser(username = "12" , password = "12")
    void 비밀번호_변경_성공() throws Exception {
        mockMvc.perform(put("/settings/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PasswordUpdateDto("12","12","1234","1234")))
                        .with(csrf())
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "12" , password = "12")
    void 비밀번호_변경_실패() throws Exception {
        mockMvc.perform(put("/settings/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordUpdateDto("12", "123", "1234", "1234")))
                .with(csrf())
        ).andExpect(status().isBadRequest());
    }

    @Test//
    @WithMockUser(username = "12" , password = "12" , roles = "ADMIN")
    void admin페이지에_model이_들어가는지() throws Exception {
        mockMvc.perform(get("/settings/admin").with(csrf()))
                .andExpect(model().attributeExists("userLoginIdList"))
                .andExpect(model().attributeExists("mountainNameList"))
                .andExpect(model().attributeExists("userRole"))
                .andExpect(view().name("account/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "12" , password = "12")
    void 등산경험치_반영이_정상적으로_되는지() throws Exception {
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(null);
        MountainExpUpdateDto mountainExpUpdateDto = new MountainExpUpdateDto("가리산", List.of("12"), "2021-10-10");
        int mountainHeight = mountainRepository.findMountainHeightByName("가리산");
        settingsService.updateMountainExp(mountainExpUpdateDto);
        Assertions.assertThat(accountEntity.getMountainExp()).isEqualTo(mountainHeight);
    }
}