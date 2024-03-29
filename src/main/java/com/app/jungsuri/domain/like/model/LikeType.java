package com.app.jungsuri.domain.like.model;


public enum LikeType {
    POST("POST"), COMMENT("COMMENT");

    private final String label;

    LikeType(String likeType) {
        this.label = likeType;
    }

    public String label() {
        return label;
    }
}
