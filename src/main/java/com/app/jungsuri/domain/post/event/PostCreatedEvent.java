package com.app.jungsuri.domain.post.event;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreatedEvent {
    private final PostEntity postEntity;
}
