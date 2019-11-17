package com.example.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
    TABLE 데이터 맵핑을 위한 Model
    entity 는 db table 간의 구조와 관계를 JPA 가 요구하는 형태로 만든
    model.
    model 에는 테이블 칼럼 값의 정보와 테이블 간의 연관 관계 정보를 가짐.
 */
@Builder // builder 사용
@Entity // jpa Entity
@Getter // user 필드 값의 getter 자동 생성
@Setter
@NoArgsConstructor // 인자 없는 생성자 자동 생성
@AllArgsConstructor // 인자를 모두 갖춘 생성자 자동 생성
@Table(name = "user") // 'user' 테이블과 매칭됨을 명시
// SpringSecurity 의 보안을 적용하기 위해 UserDetails 를 상속받음.
public class User implements UserDetails {
    @Id // primaryKey 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pk 생성전략을 DB에 위임함 (MYSQL : auto_increment)
    private Long idx;
    @Column(nullable = false, unique = true, length = 30)
    // uid column 을 명시함. NN, UQ, length 30
    private String uid;
    @Column(nullable = true, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Integer coin;
    @Column(nullable = false)
    private Integer exp;
    @Column(nullable = false)
    private Integer score;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    // 회원이 가지고 있는 권한 정보
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // JSON 결과로 출력하지 않을 데이터는 위와 같이 선언함.
    @Override
    public String getUsername() {
        return this.uid;
    }

    // uid 이외의 회원 상태 값은 사용하지 않으므로 모두 true 로 설정한다.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}
