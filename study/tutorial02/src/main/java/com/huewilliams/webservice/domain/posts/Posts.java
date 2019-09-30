package com.huewilliams.webservice.domain.posts;

import com.huewilliams.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 추가, 접근 제어 : protected
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Entity // 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id // PK 필드로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙, springboot 2.0에서는 strategy 를 지정해주어야 함
    private Long id;

    // 해당 클래스의 필드가 모두 컬럼이 됨
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스 생성
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

// Post 클래스는 Entity 클래스로 실제 DB의 테이블과 매칭될 클래스이다.