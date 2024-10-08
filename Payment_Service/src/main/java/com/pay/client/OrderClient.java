package com.pay.client;

import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pay.dto.OrderDTO;

@FeignClient(name= "ORDER-SERVICE")
public interface OrderClient {
	
	@GetMapping("/order/myOrder/{id}")
	public Optional<OrderDTO> getOrderById(@PathVariable("id") Long id);
}
