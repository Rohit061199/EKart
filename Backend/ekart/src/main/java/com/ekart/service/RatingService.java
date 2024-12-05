package com.ekart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Rating;
import com.ekart.model.User;
import com.ekart.requests.RatingRequest;

@Service
public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getAllRatingsForProduct(Long productId);

}
