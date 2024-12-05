package com.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ekart.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
	
	@Query("SELECT r FROM Rating r WHERE r.product.id=:productId")
	public List<Rating> getAllRatingsForProduct(@Param("productId") Long productId);

}
