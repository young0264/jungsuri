package com.app.jungsuri.domain.notification.enums;

import java.util.EnumMap;
import java.util.Map;

public enum NotificationType {
    POST_CREATED, POST_UPDATED, MEMBER_ENROLLMENT;
    private static final Map<NotificationType, String> enumMap;

    static {
        enumMap = new EnumMap<>(NotificationType.class);
        enumMap.put(POST_CREATED, "게시글 등록");
        enumMap.put(POST_UPDATED, "게시글 수정");
        enumMap.put(MEMBER_ENROLLMENT, "신규 회원 가입");
    }

    public static String getMessgeByType(NotificationType type) {
        return enumMap.get(type);
    }
}
