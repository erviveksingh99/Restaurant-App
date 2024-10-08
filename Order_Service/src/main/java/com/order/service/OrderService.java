package com.order.service;

import java.util.List;
import com.order.dto.RestaurantDTO;
import com.order.entity.Order;

public interface OrderService {

	public Order placeOrder(Order order)throws Exception;

	public RestaurantDTO getRestaurantDetails(Long id);
	
	public String cancelOrder(Long id);
	
	public List<Order> myOrder();
	
	public Order getMyOrderById(Long id);
}
