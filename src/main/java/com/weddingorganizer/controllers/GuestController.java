package com.weddingorganizer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weddingorganizer.exceptions.ResourceNotFoundException;
import com.weddingorganizer.models.Guest;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.GuestRepository;
import com.weddingorganizer.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/wedding/{id}")
public class GuestController {
	
	@Autowired
	GuestRepository guestRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/guests")
	public List<Guest> getAllGuests(@PathVariable Integer id){
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return user.getGuests();
	}
	
	@PostMapping("/guests")
	public Guest createGuest(@PathVariable Integer id, @Valid @RequestBody Guest guest) {
		User user = new User();
		user.setId(id);
		guest.setUser(user);
		return guestRepository.save(guest);
	}
	
	@PutMapping("/guests")
	public Guest updateGuest(@RequestParam(required = true) Integer id, @Valid @RequestBody Guest editedGuest) {
		Guest guest = guestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
		
		guest.setName(editedGuest.getName());
		guest.setInvited(editedGuest.isInvited());
		guest.setConfirmed(editedGuest.isConfirmed());
		guest.setPlusOne(editedGuest.isPlusOne());
		
		return guestRepository.save(guest);
	}
	
	@DeleteMapping("/guests")
	public ResponseEntity<?> deleteGuest(@RequestParam(required = true) Integer id){
		
		guestRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}

}
