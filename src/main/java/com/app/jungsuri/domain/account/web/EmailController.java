package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String checkEmailToken(String token, Principal principal, RedirectAttributes redirectAttributes) {
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        if (!accountEntity.isValidToken(token)) {
            redirectAttributes.addAttribute("error", "wrong.email token");
            return MAIN_REDIRECT_VIEW;
        }
        accountService.completeSignUp(accountEntity);
        return MAIN_REDIRECT_VIEW;
    }
}