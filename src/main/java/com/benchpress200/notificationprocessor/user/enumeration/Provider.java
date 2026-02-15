package com.benchpress200.notificationprocessor.user.enumeration;


import lombok.Getter;

@Getter
public enum Provider {
    LOCAL("local"),
    NAVER("naver"),
    GOOGLE("google"),
    KAKAO("kakao");

    private final String provider;

    Provider(String provider) {
        this.provider = provider;
    }
}
