package com.app.jungsuri.domain.account.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class SettingsController {

    @GetMapping("/settings")
    public String showProfile() {
        return "account/settings";
    }
}
