package com.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.order.status.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "Placed_Order") // Table is now named "Placed_Order"
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_Id;

	@NotBlank(message = "Restaurant Id cannot be blank")
	private Long restaurantId;

	@NotBlank(message = "User Id cannot be blank")
	private Long userId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_Id", referencedColumnName = "order_Id")
	private List<OrderItem> OrderItems = new ArrayList<>(); // Initialize the list

	private double totalPrice;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private LocalDateTime orderDate;
}
