package com.ekart.service;

import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Cart;
import com.ekart.model.User;
import com.ekart.requests.AddItemRequest;

@Service
public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
	
	

}
