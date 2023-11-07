package com.app.jungsuri.domain.account.controller;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.service.AccountService;
import com.app.jungsuri.domain.account.service.SettingsService;
import com.app.jungsuri.domain.account.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.dto.PasswordUpdateDto;
import com.app.jungsuri.domain.mountain.service.MountainService;
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
import org.springframework.web.multipart.MultipartFile;
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
        Integer mountain100Exp = accountService.getUserMountain100Exp(accountEntity);
        Integer mountain100ExpByPer = mountainService.convertMountain100ExpToMountain100ExpByPer(mountain100Exp);

        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        model.addAttribute("mountain100Exp", mountain100ExpByPer);
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

    /**
     * 등산 경험치 등록 및
     * account, mountain 해당 태그 등록
     * mountain_exp table에 데이터 등록
     * */
    @PatchMapping("/mountain-exp")
    @Operation(summary = "산에 따른 경험치 수정", description = "산(mountain)에 따른 경험치를 수정합니다.")
    public ResponseEntity updateMountainExp(@RequestBody MountainExpUpdateDto mountainExpUpdateDto, Principal principal) {
        try {
            settingsService.updateMountainExp(mountainExpUpdateDto);
            mountainService.createLog(mountainExpUpdateDto, accountService.findByLoginId(principal.getName()));
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/change-email")
    @Operation(summary = "이메일 변경", description = "이메일를 변경합니다.")
    public String changePassword(@RequestParam String email, Principal principal) {
        log.info("email : {}", email);
        accountService.updateEmail(principal.getName(), email);

        return "redirect:/settings/profile";
    }

    @PostMapping("/change-img")
    @Operation(summary = "프로필 이미지 변경", description = "프로필 이미지를 변경합니다.")
    public String changeProfileImg(@RequestParam MultipartFile profileImage, Principal principal) {
        accountService.updateProfileImg(principal.getName(), profileImage);
        return "redirect:/settings/profile";
    }
}
