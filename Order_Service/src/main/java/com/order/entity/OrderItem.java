package com.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "OrderItem name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "OrderItem name must contain only alphabets and spaces")
	private String name;

	@Min(value = 1, message = "Price must be at least 1")
	@Max(value = 1000, message = "Price must not exceed 1000")
	private double price;
}
