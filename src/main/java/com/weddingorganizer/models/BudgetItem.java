package com.weddingorganizer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class BudgetItem extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String type;
	
	private Integer amount;
	
	private Integer budget;
	
	private boolean editMode;
	
	@ManyToOne
	private Wedding wedding;
	
	public int getOverUnder() {
		return this.amount - this.budget;
	}
	
	
	

}
