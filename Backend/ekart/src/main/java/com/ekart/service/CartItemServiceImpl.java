package com.ekart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.Exception.CartItemException;
import com.ekart.Exception.UserException;
import com.ekart.model.Cart;
import com.ekart.model.CartItem;
import com.ekart.model.Product;
import com.ekart.model.User;
import com.ekart.repository.CartItemRepository;
import com.ekart.repository.CartRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepository;
	

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()* cartItem.getQuantity());
		
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		
		CartItem createdItem=cartItemRepository.save(cartItem);
		
		return createdItem;
		
		
		
		//return null;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		// TODO Auto-generated method stub
		
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		
		
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isItemExist(Cart cart, Product product, String size, Long userId) {
		// TODO Auto-generated method stub
		
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId);
		
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		// TODO Auto-generated method stub
		
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		
		User reqUser=userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
			return ;
		}
		
		throw new UserException("Can't remove other user's item");
		
		
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		// TODO Auto-generated method stub
		
		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		
		if(cartItem.isPresent()) {
			return cartItem.get();
		}
		
		
		throw new CartItemException("Item not found with id - "+cartItemId);
	}

}
