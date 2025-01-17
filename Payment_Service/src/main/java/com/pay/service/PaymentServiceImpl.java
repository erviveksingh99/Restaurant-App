package com.pay.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pay.client.OrderClient;
import com.pay.customexception.ResourceNotFoundException;
import com.pay.dto.OrderDTO;
import com.pay.entity.Payment;
import com.pay.repository.PaymentRepository;
import com.pay.status.PaymentStatus;
import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderClient orderClient;

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	@Transactional
	public String payAmount(Long id) {
		double totalPrice = 0.0;
		Optional<OrderDTO> dto = orderClient.getOrderById(id);
		if (dto.isPresent()) {
			OrderDTO orderDTO = dto.get();
			totalPrice = orderDTO.getTotalPrice();
		}
		else
		{
			throw new ResourceNotFoundException("User not found with id: " + id);
		}

		PaymentStatus status=PaymentStatus.DELIVERED;
		
		int updateStatus = paymentRepository.updateStatus(id, status);
		paymentRepository.flush(); // Ensure immediate update
		
		System.out.println("Data updated  "+updateStatus);
		
		String msg="Total Price " + totalPrice + " is debited from your account \n"
				+ "--==Your Order has delivered==-- \n"
				+ "=====---- Thank You ----===== \n"
				+ "********** Visit Again *********";
		return msg;
	}

	@Override
	public Payment getOrderById(Long id) {

		/*
		 * Optional<OrderDTO> obi = orderClient.getOrderById(id);
		 * 
		 * if (obi.isPresent()) { OrderDTO orderDTO = obi.get(); Payment payment = new
		 * Payment();
		 * 
		 * payment.setOrder_Id(orderDTO.getOrder_Id());
		 * payment.setRestaurantId(orderDTO.getRestaurantId());
		 * payment.setUserId(orderDTO.getUserId());
		 * 
		 * // Map OrderItemsDTO to PaymentItems List<PaymentItems> paymentItemsList =
		 * orderDTO.getOrderItems().stream().map(orderItem -> { PaymentItems paymentItem
		 * = new PaymentItems(); paymentItem.setId(orderItem.getId());
		 * paymentItem.setName(orderItem.getName());
		 * paymentItem.setPrice(orderItem.getPrice()); return paymentItem;
		 * }).collect(Collectors.toList());
		 * 
		 * payment.setOrderItems(paymentItemsList); // Set the mapped list
		 * 
		 * payment.setTotalPrice(orderDTO.getTotalPrice()); // PaymentStatus status =
		 * order.getStatus();
		 * 
		 * payment.setOrderDate(orderDTO.getOrderDate());
		 * payment.setPaymentMethod("Credit Card");
		 * 
		 * // PENDING, IN_PROGRESS, DELIVERED, CANCELLED
		 * payment.setStatus(orderDTO.getStatus());
		 * 
		 * paymentRepository.save(payment);
		 * 
		 * }
		 */

		return paymentRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("User not found with id: " + id));
	}

	@Override
	public Payment getPaymentHistoryById(Long id) {
		return paymentRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("User not found with id: " + id));
	}

	@Override
	public String deleteHistoryById(Long id) {
		
		Optional<Payment> findById = paymentRepository.findById(id);
		if(findById.isPresent())
		{
			paymentRepository.deleteById(id);
			return "Payment history is deleted who's paymentId is " + id;
		}
		else
		{
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}
}
