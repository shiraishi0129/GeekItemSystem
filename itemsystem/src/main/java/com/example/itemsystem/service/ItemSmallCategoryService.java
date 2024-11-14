package com.example.itemsystem.service;

import java.util.Optional;

import com.example.itemsystem.entity.ItemSmallCategoryEntity;

public interface ItemSmallCategoryService {

	Optional<ItemSmallCategoryEntity> getItemSmallCategoryEntityById(Long id);

}
