package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.model.Account;
import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute(new SignUpForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "login";
        }
        AccountEntity newAccount = accountService.createNewAccount(signUpForm);
        UserDetails userDetails = accountService.loadUserByUsername(newAccount.getLoginId());

        return "redirect:/";
    }

    @GetMapping("/hello")
    String hello() {
        return "hello";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }



}
