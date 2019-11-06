package com.example.rest.controller.v1;

import com.example.rest.entity.User;
import com.example.rest.model.response.CommonResult;
import com.example.rest.model.response.ListResult;
import com.example.rest.model.response.SingleResult;
import com.example.rest.repo.UserJpaRepo;
import com.example.rest.service.ResponseService;
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
    private final ResponseService responseService;

    // 각 resource 에 제목과 설명 표시
    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회함.")
    // user 테이블의 모든 데이터를 불러옴.
    // 데이터가 1개 이상일 수 있으므로 List<User> 타입으로 리턴.
    @GetMapping(value = "/user")
    public ListResult<User> findAllUser() {
        // JPA 는 기본적으로 CRUD 메소드 지원함.
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
    @GetMapping(value = "/user/{idx}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원 고유 ID", required = true) @PathVariable Integer idx ) {
        return responseService.getSingleResult(userJpaRepo.findById(idx).orElse(null));
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

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam Integer idx,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .idx(idx)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @DeleteMapping(value = "/user/{idx}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable Integer idx) {
        userJpaRepo.deleteById(idx);
        return responseService.getSuccessResult();
    }
}
