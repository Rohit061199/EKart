package com.ekart.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ekart.Exception.ProductException;
import com.ekart.model.Product;
import com.ekart.model.Size;
import com.ekart.requests.CreateProductRequest;

public interface ProductService {
	
	public Product createProduct(CreateProductRequest request);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId, Product req) throws ProductException;
	
	public Product findProductById(Long productId) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public Page<Product> getAllProducts(String category, List<String> colors, List<Size> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount
			, String sort, String stock, Integer pageNumber, Integer pageSize);
	

}
