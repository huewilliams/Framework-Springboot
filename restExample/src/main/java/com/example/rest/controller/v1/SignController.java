package com.example.rest.controller.v1;

import com.example.rest.advice.exception.UserExistException;
import com.example.rest.advice.exception.UserNotFoundException;
import com.example.rest.config.security.JwtTokenProvider;
import com.example.rest.entity.User;
import com.example.rest.advice.exception.EmailSigninFailedException;
import com.example.rest.model.response.CommonResult;
import com.example.rest.model.response.SingleResult;
import com.example.rest.model.social.KakaoProfile;
import com.example.rest.repo.UserJpaRepo;
import com.example.rest.service.ResponseService;
import com.example.rest.service.social.KakaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final KakaoService kakaoService;

    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        User user = userJpaRepo.findByUid(String.valueOf(profile.getId())).orElseThrow(UserNotFoundException::new);
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getIdx()), user.getRoles()));
    }

    @PostMapping(value = "/signup")
    public CommonResult signup(
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken
    ) {
        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        Optional<User> user = userJpaRepo.findByUid(String.valueOf(profile.getId()));
        if(user.isPresent())
            throw new UserExistException();

        userJpaRepo.save(User.builder()
                .uid(String.valueOf(profile.getId()))
                .name(profile.getNickname())
                .roles(Collections.singletonList("ROLE_USER"))
                .coin(0)
                .exp(0)
                .score(0)
                .build());
        return responseService.getSuccessResult();
    }
}
