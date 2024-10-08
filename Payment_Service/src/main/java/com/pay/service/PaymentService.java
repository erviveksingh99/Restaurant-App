package com.pay.service;

import java.util.List;
import com.pay.entity.Payment;

public interface PaymentService {

	public List<Payment> getAllPayments();

	public String payAmount(Long id);

	public Payment getOrderById(Long id);
	
	public Payment getPaymentHistoryById(Long id);
	
	public String deleteHistoryById(Long id);
	
}
