package com.example.rest.controller.v1;

import com.example.rest.entity.User;
import com.example.rest.repo.UserJpaRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"}) // UserController 를 대표하는 최상단 타이틀 영역에 표시될 값
/*
 class 내부에 final 로 선언된 객체에 대해 Constructor Injection 을
 수행함. @Autowired 로 대체 가능
 */
@RequiredArgsConstructor
@RestController // 결과 데이터를 JSON 으로 보냄
@RequestMapping(value = "/v1") // 버전 관리를 위해 /v1을 모든 리소스 주소에 적용
public class UserController {
    private final UserJpaRepo userJpaRepo;

    // 각 resource 에 제목과 설명 표시
    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회함.")
    // user 테이블의 모든 데이터를 불러옴.
    // 데이터가 1개 이상일 수 있으므로 List<User> 타입으로 리턴.
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        // JPA 는 기본적으로 CRUD 메소드 지원함.
        return userJpaRepo.findAll();
    }

    @ApiOperation(value = "회원 생성", notes = "새로운 회원을 추가함.")
    @PostMapping(value = "/user")
    // 파라미터에 대한 설명 표시
    public User save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                     @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return userJpaRepo.save(user);
    }
}
