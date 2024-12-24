package com.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.UserException;
import com.ekart.model.User;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileFromJwt(@RequestHeader("Authorization") String jwt) throws UserException{
		
		System.out.print(jwt);
		User user=userService.findUserProfileByJwt(jwt);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
