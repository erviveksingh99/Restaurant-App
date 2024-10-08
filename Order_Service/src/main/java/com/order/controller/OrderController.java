package com.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.order.dto.RestaurantDTO;
import com.order.entity.Order;
import com.order.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	

	@PostMapping
	public Order placeOrder(@Valid @RequestBody Order order) throws Exception {
		return orderService.placeOrder(order);
	}

	@GetMapping("/restaurant/{id}")
	public RestaurantDTO getRestaurantId(@PathVariable("id") Long id) {
		return orderService.getRestaurantDetails(id);
	}

	@DeleteMapping("/cancel/{id}")
	public String cancelOrder(@PathVariable("id") Long id) {
		return orderService.cancelOrder(id);
	}

	@GetMapping("/myOrder")
	public List<Order> checkOrder() {
		return orderService.myOrder();
	}
	
	@GetMapping("/myOrder/{id}")
	public Order getOrderById(@PathVariable("id") Long id)
	{
		return orderService.getMyOrderById(id);
	}
}
