package com.app.jungsuri.domain.event.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(String message) {
        EnrollmentEvent enrollmentEvent = new EnrollmentEvent(message);
        eventPublisher.publishEvent(enrollmentEvent);
    }


}
