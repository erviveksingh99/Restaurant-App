package com.restaurant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.restaurant.entity.Restaurant;

public interface RestaurantService {

	public ResponseEntity<String> createRestaurant(Restaurant rest);

	public Restaurant getRestaurantById(Long id) throws Exception;

	public ResponseEntity<List<Restaurant>> getAllRestaurant();
	
	public String removeRestaurantById(Long id);
	
}
