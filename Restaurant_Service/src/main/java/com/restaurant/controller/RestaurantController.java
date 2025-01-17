package com.restaurant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.entity.Restaurant;
import com.restaurant.service.RestaurantService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	//==> for Load balancing post set: -Dserver.port=9090
//	@Autowired
//	private Environment evn;

	@PostMapping("/add")
	public ResponseEntity<String> addRestaurant(@Valid @RequestBody Restaurant rest) {
		return restaurantService.createRestaurant(rest);
	}

	@GetMapping("/find/{id}")
	public Restaurant getRestaurantById(@PathVariable Long id) throws Exception {
		return restaurantService.getRestaurantById(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		
		//System.out.println("Post number :: "+evn.getProperty("server.port"));
		
		return restaurantService.getAllRestaurant();
	}
	
	@GetMapping("remove/{id}")
	public String removeRestaurantById(@PathVariable("id") Long id)
	{
		return restaurantService.removeRestaurantById(id);	
	}
}
