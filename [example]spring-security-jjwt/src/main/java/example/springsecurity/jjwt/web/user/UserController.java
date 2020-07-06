package example.springsecurity.jjwt.web.user;

import example.springsecurity.jjwt.config.security.JwtTokenProvider;
import example.springsecurity.jjwt.domain.User;
import example.springsecurity.jjwt.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입 시 USER로 설정
                .build()).getId();
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("not joined email."));
        if(!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("invalid password.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
