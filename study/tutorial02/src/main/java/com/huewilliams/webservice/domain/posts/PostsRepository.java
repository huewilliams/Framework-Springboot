package com.huewilliams.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자로 Dao 라고 불린다.
// JPA 에선 Repository 라고 부르며 인터페이스로 생성한다.
// JpaRepository 를 상속하면 기본적인 CRUD 메소드가 자동생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
