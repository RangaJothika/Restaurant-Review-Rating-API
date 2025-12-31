package com.codingninjas.Foodies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.repository.CustomerRepository;
import com.codingninjas.Foodies.repository.RatingRepository;
import com.codingninjas.Foodies.repository.RestaurantRepository;

@Service
public class MainService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	public void saveRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		restaurantRepository.save(restaurant);
	}

	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	public void saveCustRatingForRestaurant(Rating rating, Integer customerId, String restaurantName) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		customer.getVisitedRestaurants().add(restaurant);
		customer.getRatings().add(rating);
		customerRepository.save(customer);
		rating.setCustomer(customer);
		rating.setRestaurant(restaurant);
		ratingRepository.save(rating); // built-in method//no need to save rating of customer and restaurant they are
										// done auto as we have the mapped annotation to them with cascadetypes
										// respectively and fk in rating table will also get updated crtly
	}

	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	public List<Customer> findByVisitedRestaurants(String restaurantName) {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantRepository.findByName(restaurantName);
		return customerRepository.findByVisitedRestaurants(restaurant);
	}

	public List<Customer> getCustomerRatingGreaterThan(String restaurantName, double rating) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerRatingGreaterThan(restaurantName,rating);
	}

	public List<Rating> getAvgResturantRating(String restaurantName) {
		// TODO Auto-generated method stub
		return ratingRepository.getAvgRestaurantRating(restaurantName);
	}

}
