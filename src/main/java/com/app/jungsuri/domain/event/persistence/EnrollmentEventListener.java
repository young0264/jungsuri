package com.app.jungsuri.domain.event.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnrollmentEventListener {

//    @Async
    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        log.info("enrollmentEvent = " + event);
        log.info("event 메시지 발송 = " + event.getMessage());
    }

}
