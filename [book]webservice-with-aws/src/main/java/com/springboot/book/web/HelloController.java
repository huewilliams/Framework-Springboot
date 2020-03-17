package com.springboot.book.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 설정
public class HelloController {

    @GetMapping("/hello") // HTTP GET 요청을 받는 API로 설정
    public String hello() {
        return "hello";
    }
}
