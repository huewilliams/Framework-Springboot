package com.springboot.book.config.auth;

import com.springboot.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security의 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                // URL 별 권한 관리 옵션의 진입점(authorizeRequests가 선언되어야 antMatchers 옵션 사용 가능)
                .antMatchers("/", "/css", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // antMatchers : 권한 관리 대상 지정 옵션 (URL, HTTP 메소드 별로 관리 가능)
                // permitAll() : 전체 접근 권한 / hasRole(Role.USER.name()) : USER 권한을 가진 사람만 접근 가능
                .anyRequest().authenticated()
                // anyRequest : 설정된 값 이외의 URL들 / authenticated : 인증된 사용자만 접근 가능
                .and()
                .logout() // logout 설정의 진입점
                .logoutSuccessUrl("/") // 로그아웃 성공 시 / 주소로 리다이렉트
                .and()
                .oauth2Login() // oauth2 설정의 진입점
                .userInfoEndpoint() // oauth2 로그인 성공 후 사용자 정보를 가져올 때의 설정
                .userService(customOAuth2UserService);
        // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록함
    }
}
