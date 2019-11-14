package com.example.rest.advice;

import com.example.rest.advice.exception.AuthenticationEntryPointException;
import com.example.rest.advice.exception.EmailSigninFailedException;
import com.example.rest.advice.exception.UserNotFoundException;
import com.example.rest.model.response.CommonResult;
import com.example.rest.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
/*
    @ControllerAdvice
    Controller 의 특정 패키지나 어노테이션을 지정하면 해당 Controller 의 특정 패키지나
    어노테이션을 지정하면 해당 Controller 들에 전역으로 적용되는 코드를 작성할 수 있게 해준다.
    @RestControllerAdvice
    예외 발생시 JSON 형태로 결과를 반환하기 위해 사용
    특정 패키지나 어노테이션을 지정하지 않아 프로젝트의 모든 Controller 에 적용된다.
 */
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        return responseService.getFailResult();
    }

    @ExceptionHandler(EmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFailed(HttpServletRequest request, EmailSigninFailedException e) {
        return responseService.getFailResult(-1001, "계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, AuthenticationEntryPointException e) {
        return responseService.getFailResult(-1002, "해당 리소스에 접근하기 위한 권한이 업습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(-1003, "보유한 권한으로 접근할수 없는 리소스 입니다.");
    }

    @ExceptionHandler(Exception.class)
    /*
        Exception 발생 시 해당 Handler 를 통해서 처리함.
        Exception.class 는 최상위 예외처리 객체이므로 다른 ExceptionHandler 에서 걸러지지 않은
        예외가 있으면 최종으로 이 handler 를 거치게 된다.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    /*
        해당 Exception 발생시 HttpStatusCode 를 500으로 설정함
     */
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult();
    }
}
