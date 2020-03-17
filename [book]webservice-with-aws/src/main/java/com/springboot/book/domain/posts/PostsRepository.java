package com.springboot.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository :  DB Layer 접근자
// JpaRepository<Entity 클래스, PK 타입>를 상속하면 자동으로 기본적인 CRUD 메소드가 생성됨.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
