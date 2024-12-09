package com.example.itemsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.OrderHistoryEntity;
import com.example.itemsystem.repository.OrderHistoryReopsitory;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {
	@Autowired
	private OrderHistoryReopsitory orderHistoryRepository;

	public List<OrderHistoryEntity> getAllItem() {
		// TODO 自動生成されたメソッド・スタブ
		return orderHistoryRepository.findAll();
	}

	@Override
	public void save(OrderHistoryEntity orderHistory) {
		// TODO 自動生成されたメソッド・スタブ
		 orderHistoryRepository.save(orderHistory);
	}

}
