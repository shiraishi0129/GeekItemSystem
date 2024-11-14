package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ItemEntity;

public interface ItemService {

	List<ItemEntity> searchItems(String name,Long largeCategoryId, Long underCategoryId, Long smallCategoryId);

	List<ItemEntity> getAllItem();

	List<ItemEntity> findItems(int page, String searchQuery, String category);

	int getTotalPages(String searchQuery, String category);

	Optional<ItemEntity> getItemEntityById(Long id);



}
