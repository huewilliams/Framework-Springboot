package com.springboot.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트 자동 설정, Bean read/write
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행
    }
}
