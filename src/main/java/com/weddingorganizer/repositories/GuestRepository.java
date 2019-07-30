package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weddingorganizer.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
