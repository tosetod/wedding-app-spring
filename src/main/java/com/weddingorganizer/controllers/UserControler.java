package com.weddingorganizer.controllers;

import java.util.Calendar;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.weddingorganizer.dto.UserDto;
import com.weddingorganizer.exceptions.ResourceNotFoundException;
import com.weddingorganizer.exceptions.ValidationException;
import com.weddingorganizer.models.User;
import com.weddingorganizer.models.VerificationToken;
import com.weddingorganizer.repositories.UserRepository;
import com.weddingorganizer.security.OnRegistrationCompleteEvent;
import com.weddingorganizer.services.UserService;

@RestController
public class UserControler {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
    MessageSource messages;
	
	@GetMapping("/wedding/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return user;
	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody @Valid UserDto accountDto,
			BindingResult result, WebRequest request, Errors errors) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		User registered = userService.createUserAccount(accountDto, result);
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
						registered, request.getLocale(), appUrl
					));
		} catch (Exception e) {
			throw new ValidationException();
		}
		if (registered == null) {
			result.rejectValue("email", "message.regError");
		}
		return registered;
	}
	
	@GetMapping("/registrationConfirm")
	public ResponseEntity<?> confirmRegistration
	  (WebRequest request, Model model, @RequestParam("token") String token) {
	  
	    Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return ResponseEntity.badRequest().build();
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
	        model.addAttribute("message", messageValue);
	        return ResponseEntity.badRequest().build();
	    } 
	     
	    user.setEnabled(true); 
	    userService.saveRegisteredUser(user); 
	    return ResponseEntity.accepted().build();
	}
	
	
}
