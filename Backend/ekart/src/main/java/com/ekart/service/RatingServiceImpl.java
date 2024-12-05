package com.ekart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Product;
import com.ekart.model.Rating;
import com.ekart.model.User;
import com.ekart.repository.RatingRepository;
import com.ekart.requests.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ProductService productService;
	
	

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		// TODO Auto-generated method stub
		
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatingsForProduct(Long productId) {
		// TODO Auto-generated method stub
		
		
		
		return ratingRepository.getAllRatingsForProduct(productId);
	}

}
