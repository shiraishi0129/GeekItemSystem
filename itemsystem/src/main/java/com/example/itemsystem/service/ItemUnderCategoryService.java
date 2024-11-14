package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ItemSmallCategoryEntity;
import com.example.itemsystem.entity.ItemUnderCategoryEntity;

public interface ItemUnderCategoryService {

	Optional<ItemUnderCategoryEntity> getItemUnderCategoryEntityById(Long id);

	List<ItemSmallCategoryEntity> getSmallCategoriesByUnderId(Long id);

}
