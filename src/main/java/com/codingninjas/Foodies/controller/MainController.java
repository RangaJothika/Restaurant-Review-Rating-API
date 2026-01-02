package com.codingninjas.Foodies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.service.MainService;

@RestController
public class MainController {
	@Autowired
	MainService mainService;

	@PostMapping("/Restaurant/add")
	public void saveRestaurant(@RequestBody Restaurant restaurant) {
		mainService.saveRestaurant(restaurant);
	}

	@PostMapping("/Customer/add")
	public void saveCustomer(@RequestBody Customer customer) {
		mainService.saveCustomer(customer);
	}

	@PostMapping("/Rating/{customerId}/add/{restaurantName}")
	public void saveCustRatingForRestaurant(@RequestBody Rating rating, @PathVariable Integer customerId,
			@PathVariable String restaurantName) {
		mainService.saveCustRatingForRestaurant(rating, customerId, restaurantName);
	}

	// this method return all ratings by all customers to all restaurants
	@GetMapping("/ratings")
	public List<Rating> getAllRatings() {
		return mainService.getAllRatings();
	}

	// this method will return all customers of all restaurants
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return mainService.getAllCustomers();
	}
	@GetMapping("/customers/restaurant/{restaurantName}")
	public List<Customer> findByVisitedRestaurants(@PathVariable String restaurantName){
		return mainService.findByVisitedRestaurants(restaurantName);
	}
	@GetMapping("/customers/restaurant/{restaurantName}/{rating}")
	public List<Customer> getCustomerRatingGreaterThan(@PathVariable String restaurantName,@PathVariable double rating){
		return mainService.getCustomerRatingGreaterThan(restaurantName,rating);
	}
	@GetMapping("/restaurant/{restaurantName}/average")
	public double getAvgRestaurantRating(@PathVariable String restaurantName){
		return mainService.getAvgResturantRating(restaurantName);
	}
}
