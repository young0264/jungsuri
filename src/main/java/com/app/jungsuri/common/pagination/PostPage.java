package com.app.jungsuri.common.pagination;

public enum PostPage {
    PAGE_ROW_SIZE(5), PAGE_BTN_SIZE(5);

    private final int number;

    PostPage(int number) {
        this.number = number;
    }


    public int getValue() {
        return number;
    }

}
