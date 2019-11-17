package com.example.rest.model.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// Kakao 유저 정보를 담음
public class KakaoProfile {
    private Long id;
    private Properties properties;

    public String getNickname() {
        return this.getProperties().getNickname();
    }

    @Getter
    @Setter
    @ToString
    private static class Properties {
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }
}
