package com.weddingorganizer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class BudgetItem extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Must set type")
	private String type;
	
	@Positive(message = "Amount must be more than zero")
	@NotBlank(message = "Must set amount")
	private int amount;
	
	@Positive(message = "Budget must be more than zero")
	@NotBlank(message = "Must set budget")
	private int budget;
	
	//private boolean editMode;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	public int getOverUnder() {
		return this.budget - this.amount;
	}
	
	

}
