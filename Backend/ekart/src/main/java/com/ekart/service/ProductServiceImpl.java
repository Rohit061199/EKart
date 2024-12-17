package com.ekart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ekart.Exception.ProductException;
import com.ekart.model.Category;
import com.ekart.model.Product;
import com.ekart.model.Size;
import com.ekart.repository.CategoryRepository;
import com.ekart.repository.ProductRepository;
import com.ekart.requests.CreateProductRequest;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserService userService;

	@Override
	public Product createProduct(CreateProductRequest request) {
		// TODO Auto-generated method stub

		Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());
		if (topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(request.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel = categoryRepository.save(topLevelCategory);
		}

		Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(),
				topLevel.getName());
		if (secondLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(request.getSecondLevelCategory());
			topLevelCategory.setParentCategory(topLevel);
			topLevelCategory.setLevel(2);
			secondLevel = categoryRepository.save(topLevelCategory);
		}

		Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(),
				secondLevel.getName());
		if (thirdLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(request.getThirdLevelCategory());
			topLevelCategory.setParentCategory(secondLevel);
			topLevelCategory.setLevel(3);
			thirdLevel = categoryRepository.save(topLevelCategory);
		}

		Product product = new Product();

		product.setTitle(request.getTitle());
		product.setColor(request.getColor());
		product.setDescription(request.getDescription());
		product.setDiscountedPrice(request.getDiscountedPrice());
		product.setDiscountPercent(request.getDiscountPercent());
		product.setImageUrl(request.getImageUrl());
		product.setBrand(request.getBrand());
		product.setPrice(request.getPrice());
		product.setSizes(request.getSize());
		product.setQuantity(request.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		// TODO Auto-generated method stub

		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepository.delete(product);

		return "Product Deleted Succesfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		// TODO Auto-generated method stub

		Product product = findProductById(productId);

		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}

		// Product savedProduct=;

		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		// TODO Auto-generated method stub

		Optional<Product> opt = productRepository.findById(productId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product not found with id - " + productId);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

		for (Product pro : products) {
			System.out.println("Products object " + pro.getTitle());
		}

		if (!colors.isEmpty()) {
			// check if any of the colors match with the colors in products, if yes add to
			// the list
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		System.out.print("Products Post Stream - colors");

		for (Product pr : products) {
			System.out.println(pr.getTitle());
		}

		if (stock != null) {
			if (stock.equals("in_stock")) {
				products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			} else {
				if (stock.equals("out_of_stock")) {
					products = products.stream().filter(p -> p.getQuantity() == 0).collect(Collectors.toList());
				}
			}
		}

		System.out.print("Products Post Stream");

		for (Product pr : products) {
			System.out.println(pr.getTitle());
		}

		// say if your start index=2 and pageSize(number of products you want to list on
		// the page)=10, the n
		// the next index should be 11. And if you have less products, then the number
		// of products
		int startIndex = (int) pageable.getOffset(); // tells to leave offeset number of products
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		List<Product> pageContent = products.subList(startIndex, endIndex);

		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

		return filteredProducts;
	}

	@Override
	public List<Product> findAllProducts() {

		List<Product> products = productRepository.findAll();

		return products;
	}

}
