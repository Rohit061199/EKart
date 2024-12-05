package com.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.UserException;
import com.ekart.config.JwtProvider;
import com.ekart.model.Cart;
import com.ekart.model.User;
import com.ekart.repository.UserRepository;
import com.ekart.requests.LoginRequest;
import com.ekart.response.AuthResponse;
import com.ekart.service.CartService;
import com.ekart.service.CustomUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	private UserRepository userRepository;
	
	private JwtProvider jwtProvider;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CartService cartService;
	
	private CustomUserServiceImpl customUserServiceImpl;
	
	public AuthController(CustomUserServiceImpl customUserServiceImpl, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
		this.userRepository=userRepository;
		this.customUserServiceImpl=customUserServiceImpl;
		this.passwordEncoder=passwordEncoder;
		this.jwtProvider=jwtProvider;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		
		
		String email=user.getEmail();
		
		String password=user.getPassword();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is already in use with another account");
		}
		
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser=userRepository.save(createdUser);
		
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
		
				
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		
		authResponse.setJwt(token);
		authResponse.setMessage("Login Success");
		
		//return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		
	}

	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
		UserDetails userDetails=customUserServiceImpl.loadUserByUsername(username);
		
		if(userDetails==null) throw new BadCredentialsException("Invalid Username");
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	

}
