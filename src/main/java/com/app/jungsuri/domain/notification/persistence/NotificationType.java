package com.app.jungsuri.domain.notification.persistence;

public enum NotificationType {
    //관리자(ADMIN): 유저들 회원가입시, 회원+관리자(관리자, 정회원, 준회원) : 등산 게시물 등록시 알림, 이메일 인증 후 회원가입 완료시
    //TODO : 댓글을 단 게시물에 댓글이 추가되면?
    MEMBER_JOIN, POST_CREATED
}
