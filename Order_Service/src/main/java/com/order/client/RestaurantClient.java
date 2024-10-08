package com.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.order.dto.RestaurantDTO;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantClient {
	@GetMapping("/restaurant/find/{id}")
	public RestaurantDTO getRestaurantById(@PathVariable Long id);
}
