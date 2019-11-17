package com.example.rest.model.social;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// kakao token api mapping
public class RetKakaoAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
