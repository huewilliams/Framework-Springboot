package com.springboot.book.web;

import com.springboot.book.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// 테스트 실행시 SpringRunner 라는 스프링 실행자를 사용한다.(Spring과 JUnit 간의 연결)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
// webMVC에 집중 가능한 테스트 어노테이션 (@Controller, @ControllerAdvice 사용 가능)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc; // API 테스트 시 사용되는 클래스(테스트의 시작점)

    @WithMockUser(roles = "USER")
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //mockMVC를 통해 /hello 주소로 GET 요청을 보냄.
                .andExpect(status().isOk())
                // mvc.perform의 결과 검증(HTTP Header의 status가 200인지 검증)
                .andExpect(content().string(hello));
        // mvc.perform의 결과 검증(응답 본문의 내용이 "hello"인지 검증)
    }

    @WithMockUser(roles = "USER")
    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) // API 테스트 시 요청 파라미터 설정(단, String 값만 허용)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        // jsonPath : JSON 응답값을 필드별로 검증하는 메소드($를 기준으로 필드명 명시)
    }
}
