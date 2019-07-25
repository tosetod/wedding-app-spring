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
import com.weddingorganizer.models.BudgetItem;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.BudgetRepository;
import com.weddingorganizer.repositories.UserRepository;

@RestController
@RequestMapping("/wedding/{id}")
public class BudgetItemController {
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/budget-planner")
	public List<BudgetItem> getAllBudgetItems(@PathVariable Integer id){
		
		Optional<User> user = userRepository.findById(id);
				//userRepository.findAll().stream().filter(u -> u.getWedding().getId() == id).findAny();
		
		if (user.isPresent()) {
			return user.get().getBudgetItems();
		}
		
		throw new ResourceNotFoundException("User", "id", id);
	}
	
	@PostMapping("/budget-planner")
	public BudgetItem createBudgetItem(@PathVariable Integer id, @RequestBody BudgetItem item) {
		User user = new User();
		user.setId(id);
		item.setUser(user);
		return budgetRepository.save(item);
	}
	
	@PutMapping("/budget-planner/{itemId}")
	public BudgetItem updateBudgetItem(@PathVariable Integer itemId, @RequestBody BudgetItem editedItem) {
		BudgetItem item = budgetRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Budget Item", "id", itemId));
		
		item.setAmount(editedItem.getAmount());
		item.setBudget(editedItem.getBudget());
		item.setEditMode(editedItem.isEditMode());
		item.setType(editedItem.getType());
		
		BudgetItem updatedItem = budgetRepository.save(item);
		
		return updatedItem;
	}
	
	@DeleteMapping("/budget-planner")
	public ResponseEntity<?> deleteBudgetItem(@RequestParam(required = true) Integer id){
		
		budgetRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
}
