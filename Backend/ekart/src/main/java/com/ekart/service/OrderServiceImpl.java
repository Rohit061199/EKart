package com.ekart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.Exception.OrderException;
import com.ekart.model.Address;
import com.ekart.model.Cart;
import com.ekart.model.CartItem;
import com.ekart.model.Order;
import com.ekart.model.OrderItem;
import com.ekart.model.User;
import com.ekart.repository.AddressRepository;
import com.ekart.repository.CartRepository;
import com.ekart.repository.OrderItemRepository;
import com.ekart.repository.OrderRepository;
import com.ekart.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		
		shippingAddress.setUser(user);
		
		Address address=addressRepository.save(shippingAddress);
		user.getAddresses().add(address);
		
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setProduct(item.getProduct());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		
		Order order=new Order();
		order.setUser(user);
		order.setOrderItems(orderItems);
		order.setTotalPrice(cart.getTotalPrice());
		order.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		order.setTotalItems(cart.getTotalItem());
		order.setDiscount(cart.getDiscount());
		order.setCreatedAt(LocalDateTime.now());
		
		order.setShippingAddress(shippingAddress);
		order.setOrderStatus("PENDING");
		order.getPaymentDetails().setStatus("PENDING");
		order.setOrderDate(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(order);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		Optional<Order> order=orderRepository.findById(orderId);
		
		if(order.isPresent()) {
			return order.get();
		}
		throw new OrderException("Order not found with id - "+orderId);
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		
		List<Order> order=orderRepository.getUserOrders(userId);
		return order;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		Order order=findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return orderRepository.save(order);
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		Order order=findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		Order order=findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
		//return null;
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		Order order=findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
		//return null;
	}

	@Override
	public Order cancelledOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		Order order=findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
		//return null;
	}
	
	@Override
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
	}

}
