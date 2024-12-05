package com.ekart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Review;
import com.ekart.model.User;
import com.ekart.requests.ReviewRequest;

@Service
public interface ReviewService {
	
	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReviewsForProduct(Long productId);

}
