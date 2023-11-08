package com.app.jungsuri.domain.account.controller;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.account.dto.SignUpForm;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void 메인View가_정상적으로_잘나오는지() throws Exception{
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(model().attributeExists("error"))
                .andExpect(unauthenticated());
    }

    @Test
    void 회원가입View가_정상적으로_나오는지() throws Exception {
        mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andExpect(model().attributeExists("signUpForm"))
                .andExpect(unauthenticated());
    }

    @Test
    void 로그인페이지View가_정상적으로_나오는지() throws Exception{
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("account/login"))
                .andExpect(unauthenticated());
    }

    @Test
    void 회원가입이_정상적으로_되는지() throws Exception {

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setLoginId("testid");
        signUpForm.setPassword("123123!@#");
        signUpForm.setEmail("testid@naver.com");

        mockMvc.perform(post("/signup")
                        .param("loginId", "testid")
                        .param("email", "testid@naver.com")
                        .param("password", "123123!@#")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/account/login"))
//                .andExpect(authenticated().withUsername("testid")) //TODO
        ;

        AccountEntity accountEntity = accountRepository.findByLoginId("testid").orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        assertNotNull(accountEntity);
        Assertions.assertThat(accountEntity.getLoginId()).isEqualTo("testid");
    }

    @Test
    void WeatherEntity_모델이_정상적으로_넘어가는지() throws Exception {
        mockMvc.perform(get("/")
                        .param("city", "seoul")
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(model().attributeExists("weatherData"))
        ;
    }
}