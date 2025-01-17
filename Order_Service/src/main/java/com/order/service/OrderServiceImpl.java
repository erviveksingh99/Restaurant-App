package com.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.client.RestaurantClient;
import com.order.customexception.IdNotFoundException;
import com.order.customexception.ResourceNotFoundException;
import com.order.dto.RestaurantDTO;
import com.order.entity.Order;
import com.order.entity.OrderItem;
import com.order.repository.OrderRepository;
import com.order.status.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private RestaurantClient restaurantClient;

	@Override
	public Order placeOrder(Order order) throws Exception {

		// Calculate the total price using streams
		// Extract price from each OrderItem
		double price = order.getOrderItems().stream().mapToDouble(OrderItem::getPrice).sum(); // Sum all the prices

		// Set the total price in the order
		order.setTotalPrice(price);
		order.setStatus(OrderStatus.IN_PROGRESS);
		order.setOrderDate(LocalDateTime.now());

		// Save the order in the repository
		return orderRepo.save(order);
	}

	@Override
	public RestaurantDTO getRestaurantDetails(Long restaurantId) {
			return restaurantClient.getRestaurantById(restaurantId); 
	}

	@Override
	public String cancelOrder(Long id) {

		Optional<Order> findById = orderRepo.findById(id);
		if (findById.isPresent()) {
			orderRepo.deleteById(id); // <========
			return "Your order is canceled";
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	@Override
	public List<Order> myOrder() {
		return orderRepo.findAll();
	}

	@Override
	public Order getMyOrderById(Long id) {
		       // ====:
		return orderRepo.findById(id).orElseThrow(() -> new IdNotFoundException("User not found with id: " + id));
	}
}
