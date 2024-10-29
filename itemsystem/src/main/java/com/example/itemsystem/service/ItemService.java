package com.example.itemsystem.service;

import java.util.List;

import com.example.itemsystem.entity.ItemEntity;

public interface ItemService {

	List<ItemEntity> searchItems(String largeCategory, String UnderCategory, String smallCategory,
			String search);



}
