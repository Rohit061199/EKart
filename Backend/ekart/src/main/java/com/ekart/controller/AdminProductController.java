package com.ekart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.Exception.ProductException;
import com.ekart.model.Product;
import com.ekart.requests.CreateProductRequest;
import com.ekart.response.ApiResponse;
import com.ekart.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest prodReq){
		
		Product product=productService.createProduct(prodReq);
		
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
		
		productService.deleteProduct(productId);
		
		ApiResponse resp=new ApiResponse();
		
		resp.setMessage("Successfully Deleted Product");
		resp.setStatus(true);
		
		return new ResponseEntity<>(resp,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> products=productService.findAllProducts();
		
		return new ResponseEntity<>(products,HttpStatus.OK);
		
		
		
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product req) throws ProductException{
		
		Product product=productService.updateProduct(productId, req);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PostMapping("/create_multiple")
	public ResponseEntity<List<Product>> createMultipleProducts(@RequestBody CreateProductRequest[] req){
		List<Product> createdProducts=new ArrayList<>();
		for(CreateProductRequest re: req) {
			createdProducts.add(productService.createProduct(re));
		}
		
		return new ResponseEntity<>(createdProducts,HttpStatus.CREATED);
	}
	
	

}
