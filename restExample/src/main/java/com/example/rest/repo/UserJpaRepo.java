package com.example.rest.repo;

import com.example.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByIdx(Long idx);

    Optional<User> findByUid(String email);
}
