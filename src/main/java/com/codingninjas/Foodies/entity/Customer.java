package com.codingninjas.Foodies.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Customer {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Restaurant> getVisitedRestaurants() {
		return visitedRestaurants;
	}
	public void setVisitedRestaurants(List<Restaurant> visitedRestaurants) {
		this.visitedRestaurants = visitedRestaurants;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column
	private String name;
	public Customer(String name) {
		super();
		this.name = name;
	}
	public Customer() {}
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="customer_restaurants",joinColumns=@JoinColumn(name="customer_id"),inverseJoinColumns=@JoinColumn(name="restaurant_id"))
	private List<Restaurant> visitedRestaurants;
	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy="customer")
	@JsonIgnore
	private List<Rating> ratings;
}
