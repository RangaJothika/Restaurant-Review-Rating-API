package com.codingninjas.Foodies.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Restaurant;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	List<Customer> findByVisitedRestaurants(Restaurant restaurant);
	@Query("SELECT c FROM Customer c JOIN c.ratings r WHERE r.rating>:rating AND r.restaurant.name=:restaurantName")
	List<Customer> getCustomerRatingGreaterThan(String restaurantName, double rating);


}
