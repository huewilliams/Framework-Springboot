package com.huewilliams.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody 메소드를 모든 메소드에서 적용해준다.
@RestController
public class WebRestController {

    @GetMapping("/hello")
    public String hello() {
        // 문자열을 JSON 형태로 반환한다.
        return "HelloWorld";
    }
}
