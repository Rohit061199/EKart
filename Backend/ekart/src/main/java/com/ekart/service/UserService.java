package com.ekart.service;

import org.springframework.stereotype.Service;

import com.ekart.Exception.UserException;
import com.ekart.model.User;

@Service
public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	

}
