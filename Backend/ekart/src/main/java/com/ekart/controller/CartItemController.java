package com.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.CartItemException;
import com.ekart.Exception.UserException;
import com.ekart.model.CartItem;
import com.ekart.model.User;
import com.ekart.response.ApiResponse;
import com.ekart.service.CartItemService;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse resp=new ApiResponse();
		resp.setMessage("Item removed from the cart");
		resp.setStatus(true);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem
			, @PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		CartItem updatedItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<>(updatedItem, HttpStatus.OK);
		
		
	}
}
