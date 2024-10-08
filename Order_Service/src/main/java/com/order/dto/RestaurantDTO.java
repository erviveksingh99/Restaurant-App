package com.order.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestaurantDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	private String location;
	private String cuisineType;
	private List<MenuItemDTO> menuItems;
	private double rating;
}