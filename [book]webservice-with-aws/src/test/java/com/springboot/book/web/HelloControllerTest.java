package com.springboot.book.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
// 테스트 실행시 SpringRunner 라는 스프링 실행자를 사용한다.(Spring과 JUnit 간의 연결)
@WebMvcTest(controllers = HelloController.class)
// webMVC에 집중 가능한 테스트 어노테이션 (@Controller, @ControllerAdvice 사용 가능)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc; // API 테스트 시 사용되는 클래스(테스트의 시작점)

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //mockMVC를 통해 /hello 주소로 GET 요청을 보냄.
                .andExpect(status().isOk())
                // mvc.perform의 결과 검증(HTTP Header의 status가 200인지 검증)
                .andExpect(content().string(hello));
                // mvc.perform의 결과 검증(응답 본문의 내용이 "hello"인지 검증)
    }
}
