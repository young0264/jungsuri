package com.app.jungsuri.domain.account.web;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.persistence.SettingsService;
import com.app.jungsuri.domain.account.web.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.web.dto.PasswordUpdateDto;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
import com.app.jungsuri.domain.notification.persistence.NotificationEntity;
import com.app.jungsuri.domain.notification.persistence.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "환경설정 관련 API", description = "계정 환경설정(settings) 관련 API")
public class SettingsController {

    private final AccountService accountService;
    private final SettingsService settingsService;
    private final MountainService mountainService;
    private final NotificationService notificationService;


    @GetMapping("/profile")
    @Operation(summary = "프로필 페이지 조회", description = "프로필 페이지 조회를 조회합니다.")
    public String showProfile(Model model, Principal principal) {
        String loginId = principal.getName();
        AccountEntity accountEntity = accountService.findByLoginId(loginId);
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "account/profile";
    }

    @GetMapping("/admin")
    @Operation(summary = "관리자 페이지 조회", description = "관리자 페이지를 조회합니다.")
    public String showAdminSettingPage(Model model, Principal principal) {
        List<String> userLoginIdList = accountService.getAllUsersLoginId();
        List<String> mountainNameList = mountainService.findAllMountainsName();
        AccountEntity accountEntity = accountService.findByLoginId(principal.getName());
        model.addAllAttributes(Map.of("userLoginIdList", userLoginIdList, "mountainNameList", mountainNameList,"userRole", accountEntity.getUserRole().toString()));
        return "account/admin";
    }

    @GetMapping("/alarm")
    @Operation(summary = "알림 페이지 조회", description = "알림 페이지를 조회합니다.")
    public String showAlarmSettingPage(Model model, Principal principal) {
        String loginId = principal.getName();
        AccountEntity accountEntity = accountService.findByLoginId(loginId);
        List<NotificationEntity> notificationList = notificationService.getNotificationEntitiesByAccountId(accountEntity.getId());
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("notificationList", notificationList);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "account/alarm";
    }


    //TODO PATCH ? PUT ?
    @PutMapping("/password")
    @Operation(summary = "비밀번호 수정 처리", description = "비밀번호를 수정합니다.")
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {
        try {
            settingsService.updatePassword(passwordUpdateDto);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/mountain-exp")
    @Operation(summary = "산에 따른 경험치 수정", description = "산(mountain)에 따른 경험치를 수정합니다.")
    public ResponseEntity updateMountainExp(@RequestBody MountainExpUpdateDto mountainExpUpdateDto) {
        try {
            settingsService.updateMountainExp(mountainExpUpdateDto);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }
}
