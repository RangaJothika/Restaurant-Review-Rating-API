package com.codingninjas.Foodies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
Restaurant findByName(String restaurantName);



}
