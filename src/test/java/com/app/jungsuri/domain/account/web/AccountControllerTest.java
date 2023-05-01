package com.app.jungsuri.domain.account.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.infra.MockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
//@SpringBootTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Test
    void 메인View가_정상적으로_잘나오는지() throws Exception{
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
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
    void 로그인View가_정상적으로_나오는지() throws Exception{
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("account/login"))
                .andExpect(unauthenticated());
    }

    @Test
    void 회원가입이_정상적으로_되는지() throws Exception {
        mockMvc.perform(post("/signup")
                        .param("loginId", "testid")
                        .param("email", "testid@naver.com")
                        .param("password", "123123!@#")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
//                .andExpect(authenticated().withUsername("testid"))//TODO : Authentication should not be null- bug
        ;

        AccountEntity accountEntity = accountRepository.findByLoginId("testid");
        assertNotNull(accountEntity);
        assertThat(accountEntity.getLoginId()).isEqualTo("testid");

    }


}