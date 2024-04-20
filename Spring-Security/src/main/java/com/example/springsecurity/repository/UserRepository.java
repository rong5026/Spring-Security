package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//기본적인 CRUD가능
// @Repository라는 어노테이션이 없어도 loc됨. 이유는 Jpa상속
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy규칙 -> Username문법
    // select * from user where username = 1

    public User findByUsername(String username);
}
