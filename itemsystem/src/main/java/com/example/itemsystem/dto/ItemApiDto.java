package com.example.itemsystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class ItemApiDto {
	
	private Long id;
	private String shopName;
	private String address;
	private String item;
	private double price;
	private double stock;
	private List<OrderHistoryApiDto> orderHistories;
	
	public List<OrderHistoryApiDto> getOrderHistories() {
	    return orderHistories;
	}
	
	public void setOrderHistories(List<OrderHistoryApiDto> orderHistories) {
	    this.orderHistories = orderHistories;
	}
}
