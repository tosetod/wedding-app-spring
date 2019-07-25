package com.weddingorganizer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/wedding/{id}")
public class GuestController {
	
	@Autowired
	GuestRepository guestRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/guests")
	public List<Guest> getAllGuests(@PathVariable Integer id){
		
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return user.get().getGuests();
		}
		
		throw new ResourceNotFoundException("User", "id", id);
	}
	
	@PostMapping("/guests")
	public Guest createGuest(@PathVariable Integer id, @RequestBody Guest guest) {
		User user = new User();
		user.setId(id);
		guest.setUser(user);
		return guestRepository.save(guest);
	}
	
	@PutMapping("/guests")
	public Guest updateGuest(@RequestParam(required = true) Integer id, @RequestBody Guest editedGuest) {
		Guest guest = guestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
		
		guest.setInvited(editedGuest.isInvited());
		guest.setConfirmed(editedGuest.isConfirmed());
		guest.setPlusOne(editedGuest.getPlusOne());
		
		Guest updatedGuest = guestRepository.save(guest);
		
		return updatedGuest;
	}
	
	@DeleteMapping("/guests")
	public ResponseEntity<?> deleteGuest(@RequestParam(required = true) Integer id){
		
		guestRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}

}
