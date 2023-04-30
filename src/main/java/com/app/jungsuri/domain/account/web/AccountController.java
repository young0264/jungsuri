package com.app.jungsuri.domain.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/login")
    String login() {
        return "login";
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
