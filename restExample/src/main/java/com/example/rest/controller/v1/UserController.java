package com.example.rest.controller.v1;

import com.example.rest.entity.User;
import com.example.rest.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
/*
 class 내부에 final 로 선언된 객체에 대해 Constructor Injection 을
 수행함. @Autowired 로 대체 가능
 */
@RestController // 결과 데이터를 JSON 으로 보냄
@RequestMapping(value = "/v1")
// 버전 관리를 위해 /v1을 모든 리소스 주소에 적용
public class UserController {
    private final UserJpaRepo userJpaRepo;

    // user 테이블의 모든 데이터를 불러옴.
    // 데이터가 1개 이상일 수 있으므로 List<User> 타입으로 리턴.
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        // JPA 는 기본적으로 CRUD 메소드 지원함.
        return userJpaRepo.findAll();
    }

    @PostMapping(value = "/user")
    public User save() {
        User user = User.builder()
                .uid("test@naver.com")
                .name("테스트")
                .build();
        return userJpaRepo.save(user);
    }
}
