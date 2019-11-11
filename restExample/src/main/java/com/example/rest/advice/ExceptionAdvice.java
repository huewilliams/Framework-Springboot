package com.example.rest.advice;

import com.example.rest.exception.UserNotFoundException;
import com.example.rest.model.response.CommonResult;
import com.example.rest.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

//    @ExceptionHandler(Exception.class)
//    /*
//        Exception 발생 시 해당 Handler 를 통해서 처리함.
//        Exception.class 는 최상위 예외처리 객체이므로 다른 ExceptionHandler 에서 걸러지지 않은
//        예외가 있으면 최종으로 이 handler 를 거치게 된다.
//     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    /*
//        해당 Exception 발생시 HttpStatusCode 를 500으로 설정함
//     */
//    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
//        return responseService.getFailResult();
//    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        return responseService.getFailResult();
    }
}
