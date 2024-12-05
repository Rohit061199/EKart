package com.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.OrderException;
import com.ekart.model.Order;
import com.ekart.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersHandler(){
		List<Order> orders=orderService.getAllOrders();
		
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/confirm/{orderId}")
	public ResponseEntity<Order> confirmedOrderHandler(@PathVariable("orderId") Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/shipped/{orderId}")
	public ResponseEntity<Order> shippedOrderHandler(@PathVariable("orderId") Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.shippedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/delivered/{orderId}")
	public ResponseEntity<Order> deliveredOrderHandler(@PathVariable("orderId") Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/cancelled/{orderId}")
	public ResponseEntity<Order> cancelledOrderHandler(@PathVariable("orderId") Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.cancelledOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
	}
		
	
		
	
	
	

}
