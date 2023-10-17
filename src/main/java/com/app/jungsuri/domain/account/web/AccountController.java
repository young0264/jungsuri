package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.domain.weather.persistence.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "계정 관련 API", description = "계정(Account) 관련 API")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 합니다.")
    public String login() {
        return "account/login";
    }

    @GetMapping("/signup")
    @Operation(summary = "회원가입 페이지 조회", description = "회원가입 페이지를 조회합니다.")
    public String signup(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/signup";
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입 처리", description = "회원가입을 처리합니다.")
    public String signupSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/signup";
        }
        AccountEntity newAccount = accountService.createNewAccount(signUpForm);
        accountService.login(newAccount);
        return "redirect:/account/login";
    }
}
