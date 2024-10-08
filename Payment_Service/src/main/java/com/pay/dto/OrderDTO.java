package com.pay.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.pay.status.PaymentStatus;
import lombok.Data;


@Data
public class OrderDTO {
    private Long order_Id;
    private Long restaurantId;
    private Long userId;
    private List<OrderItemsDTO> orderItems = new ArrayList<>();  // Use camelCase and correct type
    private double totalPrice;
    private PaymentStatus status;
    private LocalDateTime orderDate;
}