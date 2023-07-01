package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.persistence.SettingsService;
import com.app.jungsuri.domain.account.web.dto.PasswordUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final AccountService accountService;
    private final SettingsService settingsService;

    @GetMapping()
    public String showProfile(Model model, Principal principal) {
        String loginId = principal.getName();
        AccountEntity accountEntity = accountService.findByLoginId(loginId);
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "account/settings";
    }

    @PutMapping("/update/password")
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {
        try {
            settingsService.updatePassword(passwordUpdateDto);
        }catch(Exception error){
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }

}
