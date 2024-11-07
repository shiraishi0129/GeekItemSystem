package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ItemEntity;

public interface ItemService {

	List<ItemEntity> searchItems(String largeCategory, String UnderCategory, String smallCategory,String search);

	List<ItemEntity> getAllItem();

	List<ItemEntity> findItems(int page, String searchQuery, String category);

	int getTotalPages(String searchQuery, String category);

	Optional<ItemEntity> getItemEntityById(Long id);



}
