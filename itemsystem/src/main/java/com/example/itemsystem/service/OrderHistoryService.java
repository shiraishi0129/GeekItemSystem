package com.example.itemsystem.service;

import java.util.List;

import com.example.itemsystem.entity.OrderHistoryEntity;

public interface OrderHistoryService {

	List<OrderHistoryEntity> getAllItem();

	void save(OrderHistoryEntity orderHistory);

}
