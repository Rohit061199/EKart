package com.ekart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.Exception.UserException;
import com.ekart.config.JwtProvider;
import com.ekart.model.User;
import com.ekart.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws UserException {
		// TODO Auto-generated method stub
		
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		
		
		throw new UserException("User not found with id - "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		
		
		String email=jwtProvider.getEmailFromToken(jwt);
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User not found with email - "+email); 
		}
		return user;
	}

}
