package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.mail.javamail.JavaMailSender;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;
//    private final JavaMailSender javaMailSender;

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/signup";
        }
        AccountEntity newAccount = accountService.createNewAccount(signUpForm);
        accountService.login(newAccount);
        return "redirect:/";
    }


}
