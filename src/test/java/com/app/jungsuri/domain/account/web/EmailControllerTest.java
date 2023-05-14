package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.infra.MockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    @WithMockUser(username="12", password="12")
    void 이메일_토큰값이_정상일때() throws Exception {
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        accountEntity.setEmailToken("123abc234"); //토큰값 강제 주입
        System.out.println(accountEntity.getEmailToken());
        mockMvc.perform(post("/check-email-token")
                                .param("token", "123abc234")
                        .with(csrf()))
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @WithMockUser(username="12", password="12")
    void 이메일_토큰값이_다를때() throws Exception {
        AccountEntity accountEntity = accountRepository.findByLoginId("12").orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        accountEntity.setEmailToken("123ab123c234"); // 랜덤 토큰값 강제 주입
        System.out.println(accountEntity.getEmailToken());
        mockMvc.perform(post("/check-email-token")
                                .param("token", "123abc234")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(model().attributeExists("error"));
    }

}