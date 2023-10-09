package com.app.jungsuri.infra.pagination;

public enum MountainPage {
    PAGE_ROW_SIZE(20), PAGE_BTN_SIZE(10);

    private final int number;

    MountainPage(int number) {
        this.number = number;
    }


    public int getValue() {
        return number;
    }
}
