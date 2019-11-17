package com.example.rest.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
// 서버에 보안 설정 적용 (리소스 접근 권한 설정)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 이므로 기본 설정 사용 안함.
                                       // 기본 설정은 비인증시 로그인 폼으로 리다이렉트 됨.
                .csrf().disable() // rest api 이므로 csrf 보안이 필요없으므로 disable 처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 인증이므로 세션 생성 안함.
                .and()
                    .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                        .antMatchers("/*/signin", "/*/signup", "/social/**").permitAll() // 가입/인증은 누구나 접근 가능
                        .antMatchers(HttpMethod.GET, "/exception/**","helloworld/**").permitAll() // helloworld로 시작하는 GET 요청 허용
                        .antMatchers("/*/users").hasRole("ADMIN")
                        .anyRequest().hasRole("USER") // 그외 나머지 요청은 인증된 회원만 접근 가능
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                    // jwt 인증 필터를 id/password 인증 필터 전에 넣는다.
    }

    @Override // ignore check swagger resource
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }
}
