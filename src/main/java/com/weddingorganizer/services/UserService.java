//package com.weddingorganizer.services;
//
//import java.util.Arrays;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.BindingResult;
//
//import com.weddingorganizer.dto.UserDto;
//import com.weddingorganizer.exceptions.EmailExistsException;
//import com.weddingorganizer.models.Role;
//import com.weddingorganizer.models.User;
//import com.weddingorganizer.models.VerificationToken;
//import com.weddingorganizer.repositories.UserRepository;
//
//@Service
//public class UserService implements IUserService {
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Transactional
//	@Override
//	public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
//		
//		if (emailExist(accountDto.getEmail())) {
//			throw new EmailExistsException(accountDto.getEmail());
//		}
//		User user = new User();
//		user.setFirstName(accountDto.getFirstName());
//		user.setLastName(accountDto.getLastName());
//		user.setEmail(accountDto.getEmail());
//		user.setAge(accountDto.getAge());
//		user.setWeddingDate(accountDto.getWeddingDate());
//		user.setPartnerName(accountDto.getPartnerName());
//		user.setPartnerAge(accountDto.getPartnerAge());
//		user.setPassword(accountDto.getPassword());
//		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//		return userRepository.save(user);
//	}
//	
//	private boolean emailExist(String email) {
//		User user = userRepository.findByEmail(email);
//		if (user != null) {
//			return true;
//		}
//		return false;
//	}
//	
//	public User createUserAccount(UserDto accountDto, BindingResult result) {
//		User registered = null;
//		try {
//			registered = registerNewUserAccount(accountDto);
//		} catch (EmailExistsException e) {
//			return null;
//		}
//		return registered;
//	}
//
//	public VerificationToken getVerificationToken(String token) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void createVerificationToken(User user, String token) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void saveRegisteredUser(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//
//}
