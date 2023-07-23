package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.persistence.SettingsService;
import com.app.jungsuri.domain.account.web.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.web.dto.PasswordUpdateDto;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
import com.app.jungsuri.domain.notification.persistence.NotificationEntity;
import com.app.jungsuri.domain.notification.persistence.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final AccountService accountService;
    private final SettingsService settingsService;
    private final MountainService mountainService;
    private final NotificationService notificationService;

    @GetMapping("/alarm")
    public String showAlarmSettingPage(Model model, Principal principal) {
        String loginId = principal.getName();
        AccountEntity accountEntity = accountService.findByLoginId(loginId);
        List<NotificationEntity> notificationList = notificationService.getNotificationEntitiesByAccountId(accountEntity.getId());
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("notificationList", notificationList);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "account/alarm";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String loginId = principal.getName();
        AccountEntity accountEntity = accountService.findByLoginId(loginId);
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "account/profile";
    }

    //TODO PATCH ? PUT ?
    @PutMapping("/password")
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {
        try {
            settingsService.updatePassword(passwordUpdateDto);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public String showAdminSettingPage(Model model) {
        List<String> userLoginIdList = accountService.getAllUsersLoginId();
        List<String> mountainNameList = mountainService.findAllMountainsName();
        model.addAllAttributes(Map.of("userLoginIdList", userLoginIdList, "mountainNameList", mountainNameList));
        return "account/admin";
    }

    @PatchMapping("/mountain-exp")
    public ResponseEntity updateMountainExp(@RequestBody MountainExpUpdateDto mountainExpUpdateDto) {
        try {
            settingsService.updateMountainExp(mountainExpUpdateDto);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }
}
