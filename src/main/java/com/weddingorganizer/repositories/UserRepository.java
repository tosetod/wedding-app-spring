package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingorganizer.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
