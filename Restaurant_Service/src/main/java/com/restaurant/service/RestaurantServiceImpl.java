package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.customexception.IdNotFoundException;
import com.restaurant.customexception.ResourceNotFoundException;
import com.restaurant.entity.Restaurant;
import com.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public ResponseEntity<String> createRestaurant(Restaurant rest) {

		restaurantRepository.save(rest); // Save the restaurant

		String saveData = "Restaurant Created";

		// Return the saved restaurant with 201
		return new ResponseEntity<>(saveData, HttpStatus.CREATED);
	}

	@Override
	public Restaurant getRestaurantById(Long id) throws Exception {
		// If restaurant is found, return
		// 200 OK
		// If not found, return 404 Not Found
//		Optional<Restaurant> findById = restaurantRepository.findById(id);
//		
//		if(findById.isPresent())
//		{
//			return restaurantRepository.findById(id).map(restaurant -> ResponseEntity.ok(restaurant))
//					.orElse(ResponseEntity.notFound().build());
//		}
//		else
//		{
//			throw new ResourceNotFoundException("User not found with id: " + id);  
//		}
		
		
			return restaurantRepository.findById(id)
					.orElseThrow(()-> 
					new IdNotFoundException
					("User not found with id: " + id));
			
	}

	@Override
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		List<Restaurant> allRest = restaurantRepository.findAll();
		return new ResponseEntity<>(allRest, HttpStatus.OK);
	}

	@Override
	public String removeRestaurantById(Long id) {
		
		Optional<Restaurant> findById = restaurantRepository.findById(id);
		if(findById.isPresent())
		{
			restaurantRepository.deleteById(id);
			return "Restaurant removed which id is "+id;
		}
		else
		{
			System.out.println("hello");
			throw new IdNotFoundException("User not found with id: " + id);
		}
	}
}
