package com.app.jungsuri.domain.account.controller;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@Slf4j
@Tag(name = "이메일 관련 API", description = "이메일(email) 관련 API")
public class EmailController {

    static String MAIN_REDIRECT_VIEW = "redirect:/";

    private final AccountService accountService;

    /**
     * 회원가입 : 인증 이메일 확인
     */

    @PostMapping("/check-email-token")
    @Operation(summary = "이메일 토큰 확인 처리", description = "이메일 토큰을 확인하여 처리합니다.")
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