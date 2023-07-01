package com.app.jungsuri.domain.event.persistence;

import org.springframework.context.ApplicationEvent;

@FunctionalInterface
public interface ApplicationEventPublisher {

    default void publishEvent(ApplicationEvent event) {
        publishEvent((Object) event);
    }

    void publishEvent(Object event);
}
