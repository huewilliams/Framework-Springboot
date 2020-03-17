package com.springboot.book.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity
// 테이블과 매칭될 Entity 클래스임을 명시
// 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 매칭(SalesManager.Java -> sales_manager table)
public class Posts {

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK 생성 규칙, GenerationType.IDENTITY : auto_increment 옵션
    private Long id;

    @Column(length = 500, nullable = false)
    // 테이블 컬럼의 옵션을 추가하기 위해 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더 패턴 클래스 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
