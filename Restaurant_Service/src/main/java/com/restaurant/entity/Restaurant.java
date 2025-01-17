package com.restaurant.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "Restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Restaurant_id")
	private Long id;

	@Column(name = "Restaurant_Name")
	@NotBlank(message = "Restaurant name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Restaurant name must contain only alphabets and spaces")
	private String name;

	@NotBlank(message = "Location name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Location name must contain only alphabets and spaces")
	private String location;

	@NotBlank(message = "cuisineType name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "cuisineType name must contain only alphabets and spaces")
	private String cuisineType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Restaurant_id", referencedColumnName = "Restaurant_id")
	private List<MenuItem> menuItems;

	@DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
	@DecimalMax(value = "5.0", inclusive = true, message = "Rating must be at most 5.0")
	@NotBlank(message = "Rating name cannot be blank")
	private double rating;

}
