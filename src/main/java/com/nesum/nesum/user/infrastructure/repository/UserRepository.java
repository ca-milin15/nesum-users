package com.nesum.nesum.user.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesum.nesum.user.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
