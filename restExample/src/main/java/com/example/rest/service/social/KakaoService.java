package com.example.rest.service.social;

import com.example.rest.advice.exception.CommunicationException;
import com.example.rest.model.social.KakaoProfile;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;

//    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId = "c685fa98f337be32a2664c7de4a04691";

    public KakaoProfile getKakaoProfile(String accessToken) {
        // Set header : Content-Type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " +  accessToken);

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), (Type) KakaoProfile.class);
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }
}
