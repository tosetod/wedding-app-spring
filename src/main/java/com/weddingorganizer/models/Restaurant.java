package com.weddingorganizer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Restaurant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Company Name is mandatory")
	@Column(columnDefinition = "nvarchar(100)")
	private String companyName;
	
	@NotBlank(message = "Phone number is mandatory")
	@Column(columnDefinition = "nvarchar(20)")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String logoUrl;
	
	@Size(min = 10, max = 255, message = "Details must be between 10 and 255 characters")
	@NotBlank(message = "Details are mandatory")
	@Column(columnDefinition = "nvarchar(255)")
	private String details;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String moreDetails;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String website;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String facebook;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String directions;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<User> users; 
	
	
}
