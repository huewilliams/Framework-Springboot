package com.example.rest.controller.v1;

import com.example.rest.config.security.JwtTokenProvider;
import com.example.rest.entity.User;
import com.example.rest.advice.exception.EmailSigninFailedException;
import com.example.rest.model.response.CommonResult;
import com.example.rest.model.response.SingleResult;
import com.example.rest.repo.UserJpaRepo;
import com.example.rest.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        User user = userJpaRepo.findByUid(id).orElseThrow(EmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new EmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @GetMapping(value = "/signup")
    public CommonResult siginin(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
                                @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                                @ApiParam(value = "이름", required = true) @RequestParam String name) {
        userJpaRepo.save(User.builder()
            .uid(id)
            .password(passwordEncoder.encode(password)) // 기본 설정 : bcrypt encoding
            .name(name)
            .roles(Collections.singletonList("ROLE_USER"))
            .build());
        return responseService.getSuccessResult();
    }
}
