package com.app.jungsuri.domain.notification.web;

import com.app.jungsuri.domain.notification.persistence.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notification")
@RequiredArgsConstructor
@Tag(name = "알림 관련 API", description = "알림(alarm) 관련 API")
public class NotificationController {

    private final NotificationService notificationService;

    @PutMapping("/check")
    @ResponseBody
    @Operation(summary = "알림 체크", description = "알림이 현재 있는지를 체크합니다.")
    public ResponseEntity checkNotification(@RequestBody CheckDto checkDto) {
        notificationService.checkNotification(checkDto.getNotificationId());
        return ResponseEntity.ok().build();
    }
}
