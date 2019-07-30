package com.weddingorganizer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.weddingorganizer.DTOs.UserDto;
import com.weddingorganizer.exceptions.ResourceNotFoundException;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.UserRepository;
import com.weddingorganizer.services.UserService;

@RestController
public class UserControler {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/wedding/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return user;
	}
	
	@PostMapping("/register")
	public User createUser(@ModelAttribute("user") @Valid UserDto accountDto,
			BindingResult result, WebRequest request, Errors errors) {
		User registered = new User();
		if (!result.hasErrors()) {
			registered = userService.createUserAccount(accountDto, result);
		}
		if (registered == null) {
			result.rejectValue("email", "message.regError");
		}
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		else {
			return registered;
		}
	}
	
	
}
