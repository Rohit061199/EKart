package com.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.ProductException;
import com.ekart.Exception.UserException;
import com.ekart.model.Rating;
import com.ekart.model.User;
import com.ekart.requests.RatingRequest;
import com.ekart.service.RatingService;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		
		Rating rating=ratingService.createRating(req, user);
		
		return new ResponseEntity<>(rating, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductRatings(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException,ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		List<Rating> ratings=ratingService.getAllRatingsForProduct(productId);
		
		return new ResponseEntity<>(ratings, HttpStatus.OK);
		
		
	}

}
