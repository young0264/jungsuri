package com.app.jungsuri.domain.weather.domain;

import lombok.Getter;

@Getter
public enum WeatherImage {
    DEFAULT("Default", "https://images.unsplash.com/photo-1526943604017-955071a1fb3e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"),
    CLEAR("Clear", "https://images.unsplash.com/photo-1604949210966-9440c324823f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjB8fHN1bnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60"),
    CLOUDS("Clouds", "https://images.unsplash.com/photo-1517685352821-92cf88aee5a5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1074&q=80"),
    THUNDERSTORM("Thunderstorm", "https://images.unsplash.com/photo-1429552077091-836152271555?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=685&q=80"),
    RAIN("Rain", "https://images.unsplash.com/photo-1620385019253-b051a26048ce?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80"),
    SNOW("Snow", "https://images.unsplash.com/photo-1431036101494-66a36de47def?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=686&q=80");

    private final String status;
    private final String imgSrc;

    WeatherImage(String status, String imgSrc) {
        this.status = status;
        this.imgSrc = imgSrc;
    }

    public static String getImgSrc(String status) {
        for (WeatherImage weatherImage : WeatherImage.values()) {
            if (weatherImage.getStatus().equals(status)) {
                return weatherImage.getImgSrc();
            }
        }
        return DEFAULT.getImgSrc();
    }
}
