package com.app.jungsuri.domain.notification.web;

import com.app.jungsuri.domain.notification.persistence.Greeting;
import com.app.jungsuri.domain.notification.persistence.HelloMessage;
import lombok.Getter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {

    @GetMapping("/testNotification")
    public String testNotification() {
        return "/index";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
