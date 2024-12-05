package com.ekart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Product;
import com.ekart.model.Review;
import com.ekart.model.User;
import com.ekart.repository.ProductRepository;
import com.ekart.repository.ReviewRepository;
import com.ekart.requests.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		// TODO Auto-generated method stub
		
		Review review=new Review();
		
		Product product=productService.findProductById(req.getProductId());
		
		review.setProduct(product);
		review.setUser(user);
		
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReviewsForProduct(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllReviewsForProduct(productId);
	}

}
