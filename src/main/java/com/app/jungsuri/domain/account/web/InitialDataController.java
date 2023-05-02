package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * initial data 초기화
 */

@Controller
public class AccountPostController {

    @Autowired
    private AccountService accountService;


    @PostConstruct
    public String init() {
        SignUpForm signUpForm = new SignUpForm("12", "12@naver.com", "12",",","","","");
        accountService.createNewAccount(signUpForm);
        return "main";
    }
}
