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
		List<Restaurant> restaurantList = customer.getVisitedRestaurants();
		List<Restaurant> newRestaurantList = new ArrayList<>();
		for (Restaurant res : restaurantList) {
			Restaurant restaurant = restaurantRepository.findById(res.getId()).get();
			newRestaurantList.add(restaurant);// we need to manually do this,or hibernate tries to attach a new record
												// eventho same exists because it cant fetch itself wo us specifing
												// findbyid()
		}
		customer.setVisitedRestaurants(newRestaurantList);
		customerRepository.save(customer);
	}

	public void saveCustRatingForRestaurant(Rating rating, Integer customerId, String restaurantName) {// when user
																										// clicks to
																										// send rating
																										// only rating
																										// is in
																										// reqbody,no
																										// custoemrs or
																										// restaurant
																										// fields with
																										// ids are there
																										// so we set
																										// them here and
																										// in the url
																										// the they get
																										// attached from
																										// frontend so
																										// we need to
																										// set them in
																										// feilds based
																										// on them and
																										// then progress
																										// further
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		if (!customer.getVisitedRestaurants().contains(restaurant))
			customer.getVisitedRestaurants().add(restaurant);// this and the below lines are auto does in db but in
		// mem(session (cache)) they need to be manually done or if
		// we get data from in mem session cache wo thsi we get
		// wrong resuts
		customer.getRatings().add(rating);
		restaurant.getRatings().add(rating);
//		customerRepository.save(customer);the add(rating) lines itself update in mem so its not needed and the ratingsave itself update the db so this save cust.restaurant is nto needed
//		restaurantRepository.save(restaurant);
		rating.setCustomer(customer);
		rating.setRestaurant(restaurant);
		ratingRepository.save(rating);
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
		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		return customerRepository.findByVisitedRestaurants(restaurant);
	}

	public List<Customer> getCustomerRatingGreaterThan(String restaurantName, double rating) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerRatingGreaterThan(restaurantName, rating);
	}

	public double getAvgResturantRating(String restaurantName) {
		// TODO Auto-generated method stub
		return ratingRepository.getAvgRestaurantRating(restaurantName);
	}

}
