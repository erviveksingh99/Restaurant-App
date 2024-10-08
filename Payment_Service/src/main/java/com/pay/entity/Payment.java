package com.pay.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pay.status.PaymentStatus;

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
import lombok.Data;


@Entity
@Data
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long order_Id;

	private Long restaurantId;

	private Long userId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId", referencedColumnName = "paymentId")
	private List<PaymentItems> OrderItems = new ArrayList<>(); // Initialize the list

	private double totalPrice;
	
	// Paid, Pending, Failed
	@Enumerated(EnumType.STRING)
	private PaymentStatus status; //Payment Status like PENDING, SUCCESSFUL

	private LocalDateTime orderDate;
    
    private String paymentMethod; //Credit Card, Debit Card, UPI
   
}
