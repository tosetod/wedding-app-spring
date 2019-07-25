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
	private String firstName;
	private String lastName;
	private String email;
	private byte age;
	private Date weddingDate;
	private String partnerName;
	private byte partnerAge;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "wedding_id", referencedColumnName = "id")
//	private Wedding wedding;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<BudgetItem> budgetItems;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Guest> guests;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
//	private Restaurant restaurant;
	
	@ManyToOne
	private Restaurant restaurant;
  

}
