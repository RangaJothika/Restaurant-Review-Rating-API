package com.codingninjas.Foodies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingninjas.Foodies.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating,Integer>{

	@Query(value="SELECT AVG(r) FROM rating r WHERE r.restaurant.name=?1",nativeQuery=true)
	List<Rating> getAvgRestaurantRating(String restaurantName);
}
