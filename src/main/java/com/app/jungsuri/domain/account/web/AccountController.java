package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.domain.weather.persistence.WeatherService;
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
public class AccountController {
    private final AccountService accountService;
    private final WeatherService weatherService;

    @GetMapping("/")
    public String mainPage(@RequestParam(value="city", defaultValue="seoul") String city, Principal principal, Model model) {
        boolean emailValid = accountService.isEmailValid(principal);
        WeatherEntity weatherEntity = weatherService.getWeatherData(city);
        if (!emailValid) {
            model.addAttribute("error", "이메일 인증이 되지않은 계정입니다.");
        }
        model.addAttribute("weatherData", weatherEntity);
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
