package com.weddingorganizer.services;

import com.weddingorganizer.DTOs.UserDto;
import com.weddingorganizer.exceptions.EmailExistsException;
import com.weddingorganizer.models.User;

public interface IUserService {
	
	User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
	
}
