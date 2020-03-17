package com.springboot.book.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertThat : assertj 테스트 검증 라이브러리의 검증 메소드, 메소드 체이닝 지원
        // isEqualTo : assertj의 동등 비교 메소드. assertThat 과 isEqualTo 값이 같을 때만 성공
    }
}
