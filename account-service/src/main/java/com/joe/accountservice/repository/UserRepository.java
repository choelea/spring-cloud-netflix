package com.joe.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.accountservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
