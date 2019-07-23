package com.weddingorganizer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Wedding extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(mappedBy = "wedding")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wedding")
	private List<BudgetItem> budgetItems;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wedding")
	private List<Guest> guests;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
	
	public Wedding () {
		
	}
	
//	@ManyToOne
//	private Restaurant restaurant;
}
