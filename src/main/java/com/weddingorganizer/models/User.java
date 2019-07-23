package com.weddingorganizer.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
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
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wedding_id", referencedColumnName = "id")
	private Wedding wedding;
	
  

}
