package com.example.loginapi.Repository;

import com.example.loginapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); // 메서드를 선언만 함, 자동으로 Spring Date JPA가 이름과 entity의 필드를 보고 메서드의 내용을 작성함.
}
