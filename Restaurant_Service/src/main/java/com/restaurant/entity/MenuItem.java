package com.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "Menu_Item")
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "MenuItem name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "MenuItem name must contain only alphabets and spaces")
	private String name;

	@Min(value = 1, message = "Price must be at least 1")
	@Max(value = 1000, message = "Price must not exceed 1000")
	private double price;

	@NotBlank(message = "Description name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Description name must contain only alphabets and spaces")
	private String description;

}
