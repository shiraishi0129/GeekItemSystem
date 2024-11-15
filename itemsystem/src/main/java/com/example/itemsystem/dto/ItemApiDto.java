package com.example.itemsystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class ItemApiDto {
	
	private Long id;
	private String shopname;
	private String address;
	private ItemDetailDto item;
	private List<OrderHistoryApiDto> orderHistories;
	
	public List<OrderHistoryApiDto> getOrderHistories() {
	    return orderHistories;
	}
	
	public void setOrderHistories(List<OrderHistoryApiDto> orderHistories) {
	    this.orderHistories = orderHistories;
	}
}
