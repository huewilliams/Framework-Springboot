package com.example.rest.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloworldString() {
        return "hello world";
    }

    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    /*
     @ResponseBody를 지정하지 않으면 return에 지정된 이름으로 된 파일을 프로젝트 경로에서
     찾아서 응답한다.
     */
    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        return "helloworld";
    }

    @Getter
    @Setter
    public static class Hello {
        private String message;
    }
}
