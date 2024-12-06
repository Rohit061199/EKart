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
import com.ekart.model.Review;
import com.ekart.model.User;
import com.ekart.requests.ReviewRequest;
import com.ekart.service.ReviewService;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Review review= reviewService.createReview(req, user);
		
		return new ResponseEntity<>(review, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getAllReviewsForProduct(@PathVariable Long productId,@RequestHeader("Authorization") String jwt ) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		List<Review> reviews=reviewService.getAllReviewsForProduct(productId);
		
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

}
