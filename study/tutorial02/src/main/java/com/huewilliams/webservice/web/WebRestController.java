package com.huewilliams.webservice.web;

import com.huewilliams.webservice.domain.posts.PostsRepository;
import com.huewilliams.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody 메소드를 모든 메소드에서 적용해준다.
@RestController
// 모든 필드를 인자값으로 하는 생성자를 생성해줌.
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        // 문자열을 JSON 형태로 반환한다.
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
    }
}
