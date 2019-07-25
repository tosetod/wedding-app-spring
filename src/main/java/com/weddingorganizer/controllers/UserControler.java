package com.weddingorganizer.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weddingorganizer.exceptions.ResourceNotFoundException;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.UserRepository;

@RestController
public class UserControler {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/wedding/{id}")
	public User getUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return user.get();
		}
		
		throw new ResourceNotFoundException("User", "id", id);
	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	
}
