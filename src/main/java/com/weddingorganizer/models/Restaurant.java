package com.weddingorganizer.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Restaurant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String companyName;
	private String phone;
	private String logoUrl;
	private String details;
	private String moreDetails;
	private String website;
	private String facebook;
	private String directions;
	
	@OneToMany(mappedBy = "restaurant")
	private List<Wedding> weddings; 
	
	@OneToOne(mappedBy = "restaurant")
	private Wedding wedding;
	
	
}
