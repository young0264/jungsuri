package com.app.jungsuri.domain.notification.web;

import com.app.jungsuri.domain.notification.persistence.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PutMapping("/check")
    @ResponseBody
    public ResponseEntity checkNotification(@RequestBody CheckDto checkDto) {
        notificationService.checkNotification(checkDto.getNotificationId());
        return ResponseEntity.ok().build();
    }
}
