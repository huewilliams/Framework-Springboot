package com.springboot.book.web;

import com.springboot.book.config.auth.LoginUser;
import com.springboot.book.config.auth.dto.SessionUser;
import com.springboot.book.service.posts.PostsService;
import com.springboot.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // @LoginUser 를 통해 세션 정보를 가져올 수 있음.

        model.addAttribute("posts", postsService.findAllDesc());
        // model : 서버 템플릿 엔진에서 사용하는 객체를 저장함.

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    private String postsUpdate(@PathVariable Long id,
                               Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
