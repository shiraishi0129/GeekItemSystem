package com.example.itemsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.repository.ShopItemRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ShopItemServiceServiceImpl implements ShopItemService {
	
	@Autowired
	private ShopItemRepository shopItemRepository;

	public ShopItemEntity findByShopId(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return  shopItemRepository.findById(id).orElse(null);
	}

}
