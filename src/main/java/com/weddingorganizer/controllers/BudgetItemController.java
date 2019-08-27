package com.weddingorganizer.controllers;

import java.net.URI;
import java.net.URISyntaxException;
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
import com.weddingorganizer.models.BudgetItem;
import com.weddingorganizer.models.User;
import com.weddingorganizer.repositories.BudgetRepository;
import com.weddingorganizer.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/wedding/{id}")
public class BudgetItemController {
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/budget-planner")
	public List<BudgetItem> getAllBudgetItems(@PathVariable Integer id){
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
			return user.getBudgetItems();
	}
	
	@PostMapping("/budget-planner")
	public ResponseEntity<?> createBudgetItem(@PathVariable Integer id, @Valid @RequestBody BudgetItem item) throws URISyntaxException {
		User user = new User();
		user.setId(id);
		item.setUser(user);
		budgetRepository.save(item);
		return ResponseEntity.created(this.budgetItemsUri(id)).body(item);
	}
	
	@PutMapping("/budget-planner/{itemId}")
	public BudgetItem updateBudgetItem(@PathVariable Integer itemId, @Valid @RequestBody BudgetItem editedItem) {
		BudgetItem item = budgetRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Budget Item", "id", itemId));
		
		item.setAmount(editedItem.getAmount());
		item.setBudget(editedItem.getBudget());
		//item.setEditMode(editedItem.isEditMode());
		item.setType(editedItem.getType());
		
		return budgetRepository.save(item);
	}
	
	@DeleteMapping("/budget-planner")
	public ResponseEntity<?> deleteBudgetItem(@RequestParam(required = true) Integer id){
		try {
			budgetRepository.deleteById(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Budget item", "ID", id);
		}
		
		return ResponseEntity.ok().build();
	}
	
	private URI budgetItemsUri(Integer userId) throws URISyntaxException {
		return new URI("http://localhost:8080/wedding/" + userId + "/budget-planner");
	}
}
