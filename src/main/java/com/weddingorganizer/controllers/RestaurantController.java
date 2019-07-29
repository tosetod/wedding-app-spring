package com.weddingorganizer.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		
		if (restaurant.isPresent()) {
			return restaurant.get();
		}
		
		throw new ResourceNotFoundException("Restaurant", "ID", id);
	}
	
	@PostMapping("/restaurants/new")
	public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
		
		return restaurantRepository.save(restaurant);
	}

	@GetMapping("/wedding/{id}/restaurant")
	public Restaurant getUserRestaurant(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get().getRestaurant();
		}
		
		throw new ResourceNotFoundException("User", "id", id);
	}
	
	@PatchMapping("/restaurants")
	public ResponseEntity<?> addRestaurantToUser(@RequestParam(required = true) Integer userId, @RequestParam(required = true) Integer restaurantId) {
		Optional<User> userOptional = userRepository.findById(userId);
		Restaurant restaurant = new Restaurant();
		restaurant.setId(restaurantId);
		
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setRestaurant(restaurant);
			userRepository.save(user);
			return ResponseEntity.ok().build();
		}
		
		throw new ResourceNotFoundException("User", "ID", userId);
	}
	
	@DeleteMapping("/wedding/{id}/restaurant")
	public ResponseEntity<?> removeRestaurant(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			User editedUser = user.get();
			editedUser.setRestaurant(null);
			userRepository.save(editedUser);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
}
