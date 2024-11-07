package com.example.itemsystem.dto;

import lombok.Data;

@Data
public class OrderHistoryApiDto {
	  
	private Long id;
	private String productNname;
	private String managerName;
	private Long orderQuantity;
	private Long totalAmount;
}

