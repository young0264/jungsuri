package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.web.dto.PasswordUpdateDto;
import com.app.jungsuri.infra.MockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
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


    @Test
    @WithMockUser(username = "12" , password = "12")
    void settings_페이지이동() throws Exception {
        mockMvc.perform(get("/settings/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("accountEntity"))
                .andExpect(model().attributeExists("passwordUpdateDto"))
                .andExpect(view().name("account/profile"));
    }
//.contentType(MediaType.APPLICATION_JSON)
//.content(objectMapper.writeValueAsString(dto))
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

}