package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ItemSmallCategoryEntity;
import com.example.itemsystem.entity.ItemUnderCategoryEntity;
import com.example.itemsystem.repository.ItemSmallCategoryRepository;
import com.example.itemsystem.repository.ItemUnderCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemUnderCategoryServiceImpl implements ItemUnderCategoryService {

	@Autowired
	private ItemUnderCategoryRepository itemUnderCategoryRepository;
	@Autowired
	private ItemSmallCategoryRepository itemSmallCategoryRepository;
	
	@Override
	public Optional<ItemUnderCategoryEntity> getItemUnderCategoryEntityById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return itemUnderCategoryRepository.findById(id);
	}

	@Override
	public List<ItemSmallCategoryEntity> getSmallCategoriesByUnderId(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return itemSmallCategoryRepository.findByitemUnderCategory_Id(id);
	}

}
