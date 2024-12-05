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

import com.ekart.Exception.OrderException;
import com.ekart.Exception.UserException;
import com.ekart.model.Address;
import com.ekart.model.Order;
import com.ekart.model.User;
import com.ekart.service.OrderService;
import com.ekart.service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException{
		User user=userService.findUserProfileByJwt(jwt);
		Order order=orderService.createOrder(user, shippingAddress);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>> getUserOrders(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		List<Order> orders=orderService.userOrderHistory(user.getId());
		
		return new ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("id")Long id,@RequestHeader("Authorization") String jwt )throws UserException, OrderException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Order order=orderService.findOrderById(id);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
	}

}
