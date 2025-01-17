package com.pay.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pay.client.OrderClient;
import com.pay.dto.OrderDTO;
import com.pay.entity.Payment;
import com.pay.entity.PaymentItems;
import com.pay.repository.PaymentRepository;
import com.pay.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymetService;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderClient orderClient;
	

	@GetMapping("/pay/{id}")
	public String payAmount(@PathVariable("id") Long id) {
		
		return paymetService.payAmount(id);
	}

	@GetMapping("/history")
	public List<Payment> getAllPayments() {
		return paymetService.getAllPayments();
	}

	@GetMapping("/history/{id}")
	public Payment getPaymentHistoryById(@PathVariable("id") Long id) {
		return paymetService.getPaymentHistoryById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteHistoryById(@PathVariable("id") Long id) {
		return paymetService.deleteHistoryById(id);
	}

	// Inter service Communication with Order-Service.
	@GetMapping("/myOrder/{id}")
	public Payment getOrderById(@PathVariable("id") Long id) {
		
		Optional<OrderDTO> obi = orderClient.getOrderById(id);

		boolean b=obi.isPresent();
		System.out.println(b);
		
		if (obi.isPresent()) {
			OrderDTO orderDTO = obi.get();
			Payment payment = new Payment();

			payment.setOrder_Id(orderDTO.getOrder_Id());
			payment.setRestaurantId(orderDTO.getRestaurantId());
			payment.setUserId(orderDTO.getUserId());

			// Map OrderItemsDTO to PaymentItems
			List<PaymentItems> paymentItemsList = orderDTO.getOrderItems().stream().map(orderItem -> {
				PaymentItems paymentItem = new PaymentItems();
				paymentItem.setId(orderItem.getId());
				paymentItem.setName(orderItem.getName());
				paymentItem.setPrice(orderItem.getPrice());
				return paymentItem;
			}).collect(Collectors.toList());

			payment.setOrderItems(paymentItemsList); // Set the mapped list

			payment.setTotalPrice(orderDTO.getTotalPrice());
			// PaymentStatus status = order.getStatus();

			payment.setOrderDate(orderDTO.getOrderDate());
			payment.setPaymentMethod("Credit Card");

			// PENDING, IN_PROGRESS, DELIVERED, CANCELLED
			payment.setStatus(orderDTO.getStatus());

			paymentRepository.save(payment);
		}
		return paymetService.getOrderById(id);
	}
}
