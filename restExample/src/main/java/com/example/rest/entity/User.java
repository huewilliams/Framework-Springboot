package com.example.rest.entity;

import lombok.*;

import javax.persistence.*;

/*
    TABLE 데이터 맵핑을 위한 Model
    entity 는 db table 간의 구조와 관계를 JPA 가 요구하는 형태로 만든
    model.
    model 에는 테이블 칼럼 값의 정보와 테이블 간의 연관 관계 정보를 가짐.
 */
@Builder // builder 사용
@Entity // jpa Entity
@Getter // user 필드 값의 getter 자동 생성
@NoArgsConstructor // 인자 없는 생성자 자동 생성
@AllArgsConstructor // 인자를 모두 갖춘 생성자 자동 생성
@Table(name = "user") // 'user' 테이블과 매칭됨을 명시
public class User {
    @Id // primaryKey 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pk 생성전략을 DB에 위임함 (MYSQL : auto_increment)
    private long msrl;
    @Column(nullable = false, unique = true, length = 30)
    // uid column 을 명시함. NN, UQ, length 30
    private String uid;
    @Column(nullable = false, length = 100)
    private String name;
}
