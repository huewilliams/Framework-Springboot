package com.springboot.book.web;

import com.springboot.book.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 설정
public class HelloController {

    @GetMapping("/hello") // HTTP GET 요청을 받는 API로 설정
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져옴.
        // 외부에서 "name" 으로 넘긴 파라미터를 String name에 저장함.
        return new HelloResponseDto(name, amount);

    }
}
