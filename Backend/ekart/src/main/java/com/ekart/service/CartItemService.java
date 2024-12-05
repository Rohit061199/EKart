package com.ekart.service;

import org.springframework.stereotype.Service;

import com.ekart.Exception.CartItemException;
import com.ekart.Exception.UserException;
import com.ekart.model.Cart;
import com.ekart.model.CartItem;
import com.ekart.model.Product;

@Service
public interface CartItemService {
	
	public CartItem createCartItem(CartItem carItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
	

}
