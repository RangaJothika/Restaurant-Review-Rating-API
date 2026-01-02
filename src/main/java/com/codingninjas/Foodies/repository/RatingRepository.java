package com.codingninjas.Foodies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingninjas.Foodies.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating,Integer>{

	@Query(value="SELECT AVG(r.rating) FROM rating r JOIN restaurant res ON r.restaurant_id = res.id WHERE res.name = ?1", nativeQuery=true)
	double getAvgRestaurantRating(String restaurantName);
}
