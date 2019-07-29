package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingorganizer.models.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
