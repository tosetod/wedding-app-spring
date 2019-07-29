package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingorganizer.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
