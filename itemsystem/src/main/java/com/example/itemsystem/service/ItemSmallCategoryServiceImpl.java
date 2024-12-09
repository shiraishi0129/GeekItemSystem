package com.example.itemsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ItemSmallCategoryEntity;
import com.example.itemsystem.repository.ItemSmallCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemSmallCategoryServiceImpl implements ItemSmallCategoryService {
	
	@Autowired
	private ItemSmallCategoryRepository itemSmallCategoryRepository;

	@Override
	public Optional<ItemSmallCategoryEntity> getItemSmallCategoryEntityById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return itemSmallCategoryRepository.findById(id);
	}

}
