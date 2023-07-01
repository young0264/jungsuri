package com.app.jungsuri.domain.event.persistence;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final ApplicationEventPublisher eventPublisher;

    public void sendMessage(String message) {
        log.info(String.format("메시지 전송 : [%s]", message));
        eventPublisher.publishEvent(new EnrollmentEvent(message));
    }
}
