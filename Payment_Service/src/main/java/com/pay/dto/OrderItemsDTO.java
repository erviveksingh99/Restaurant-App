package com.pay.dto;

import lombok.Data;

@Data
public class OrderItemsDTO {
    private Long id;
    private String name;
    private double price;
}
