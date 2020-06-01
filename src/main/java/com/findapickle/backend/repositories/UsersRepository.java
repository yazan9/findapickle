package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}