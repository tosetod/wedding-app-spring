package com.weddingorganizer.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.weddingorganizer.validations.PasswordMatches;
import com.weddingorganizer.validations.ValidEmail;

import lombok.Data;

@Data
@PasswordMatches
public class UserDto {
	@NotNull
    @NotEmpty
    private String firstName;
	
    private String lastName;
	
	@ValidEmail
    @NotNull
    @NotEmpty
    private String email;
     
    @NotNull
    @NotEmpty
    private String password;
    
    private String matchingPassword;
	
    @NotNull
    @NotEmpty
	private byte age;
    
	private Date weddingDate;
    
	private String partnerName;
	
	private byte partnerAge;
}
