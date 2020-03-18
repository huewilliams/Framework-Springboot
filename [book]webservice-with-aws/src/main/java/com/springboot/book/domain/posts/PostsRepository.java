package com.springboot.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Repository :  DB Layer 접근자
// JpaRepository<Entity 클래스, PK 타입>를 상속하면 자동으로 기본적인 CRUD 메소드가 생성됨.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // 스프링 부트가 제공하지 않는 메소드는 쿼리로 작성할 수 있다.
    List<Posts> findAllDesc();
}
