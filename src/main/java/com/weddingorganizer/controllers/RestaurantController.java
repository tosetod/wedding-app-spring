package com.weddingorganizer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.weddingorganizer.exceptions.ResourceNotFoundException;
import com.weddingorganizer.models.Restaurant;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.RestaurantRepository;
import com.weddingorganizer.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();

	}
	
	@GetMapping("/restaurants/{id}")
	public Restaurant getRestaurant(@PathVariable Integer id) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "ID", id));
		
		return restaurant;
	}
	
	@PostMapping("/restaurants/new")
	public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
		
		return restaurantRepository.save(restaurant);
	}

	@GetMapping("/wedding/{id}/restaurant")
	public Restaurant getUserRestaurant(@PathVariable Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		Restaurant restaurant = user.getRestaurant();
		if (restaurant != null) {
			return restaurant;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/restaurants")
	public ResponseEntity<?> addRestaurantToUser(@RequestParam(required = true) Integer userId, @RequestParam(required = true) Integer restaurantId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Restaurant restaurant = new Restaurant();
		restaurant.setId(restaurantId);
		
		user.setRestaurant(restaurant);
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/wedding/{id}/restaurant")
	public ResponseEntity<?> removeRestaurant(@PathVariable Integer id){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		user.setRestaurant(null);
		userRepository.save(user);
			
		return ResponseEntity.ok().build();
		
	}
}
