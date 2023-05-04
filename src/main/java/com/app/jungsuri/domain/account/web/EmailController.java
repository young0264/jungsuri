package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@Slf4j
public class EmailController {

    static String MAIN_REDIRECT_VIEW = "redirect:/";

    private final AccountService accountService;

    /**
     * 회원가입 : 인증 이메일 확인
     */

    @PostMapping("/check-email-token")
    public String checkEmailToken(String token, Principal principal, Model model) {
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        if (!accountEntity.isValidToken(token)) {
            System.out.println("check email token token principal : " + accountEntity.getEmailToken());
            System.out.println("check email token token " +token);
            System.out.println("check email token error");
            model.addAttribute("error", "error");
        }
        accountService.completeSignUp(accountEntity);
        return MAIN_REDIRECT_VIEW;
    }
}