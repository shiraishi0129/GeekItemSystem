package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ItemLargeCategoryEntity;
import com.example.itemsystem.entity.ItemUnderCategoryEntity;
import com.example.itemsystem.form.ItemLargelCategoryForm;

public interface ItemLargeCategoryService {
	public void saveItemLargeCategory(ItemLargelCategoryForm itemLargeCategoryForm);

	public List<ItemLargeCategoryEntity> getAllItemLargeCategory();

	public Optional<ItemLargeCategoryEntity> getItemLargeCategoryEntityById(Long id);

	public List<ItemUnderCategoryEntity> getUnderCategoriesByLargeId(Long id);
}
