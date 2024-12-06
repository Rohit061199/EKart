package com.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.ProductException;
import com.ekart.Exception.UserException;
import com.ekart.model.Cart;
import com.ekart.model.User;
import com.ekart.requests.AddItemRequest;
import com.ekart.response.ApiResponse;
import com.ekart.service.CartService;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/find")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt)throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Cart cart=cartService.findUserCart(user.getId());
		
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		cartService.addCartItem(user.getId(),req);
		
		ApiResponse resp=new ApiResponse();
		resp.setMessage("Item added successfully");
		resp.setStatus(true);
		
		return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
	}
}