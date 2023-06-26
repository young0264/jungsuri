package com.app.jungsuri.domain.event.web;

import com.app.jungsuri.domain.event.persistence.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class EventController {

    private final EventService eventService;

    @ResponseBody
    @GetMapping("/event/enrollment/{message}")
    public String enrollment(@PathVariable String message) {
        eventService.sendMessage(message);
        log.info("message가 정상적으로 전송되었습니다. : " + message);
        return "success";
    }

}
