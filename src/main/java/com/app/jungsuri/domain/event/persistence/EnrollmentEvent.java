package com.app.jungsuri.domain.event.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Getter
public class EnrollmentEvent {
//    protected final Enrollment enrollment;
//    private final String title;
    private final String message;

    public EnrollmentEvent(String message) {
//        this.title = title;
        this.message = message;

    }
}
