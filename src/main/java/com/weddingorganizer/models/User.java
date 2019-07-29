package com.weddingorganizer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "[user]")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	private String lastName;
	
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@Min(value = 16, message = "Age should not be less than 16")
    @Max(value = 120, message = "Age out of bounds")
	private byte age;
	
	@Future
	private Date weddingDate;
	private String partnerName;
	
	@Min(value = 16, message = "Age should not be less than 16")
    @Max(value = 120, message = "Age out of bounds")
	private byte partnerAge;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<BudgetItem> budgetItems;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Guest> guests;
	
	@ManyToOne
	private Restaurant restaurant;
  

}
