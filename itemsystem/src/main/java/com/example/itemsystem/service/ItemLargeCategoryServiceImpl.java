package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entiry.ItemLargeCategoryEntity;
import com.example.itemsystem.form.ItemLargelCategoryForm;
import com.example.itemsystem.repository.ItemLargeCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemLargeCategoryServiceImpl implements ItemLargeCategoryService {
	
	@Autowired
	private ItemLargeCategoryRepository itemLargeCategoryRepository;

	@Override
	public void saveItemLargeCategory(ItemLargelCategoryForm itemLargeCategoryForm) {
		// TODO 自動生成されたメソッド・スタブ
		ItemLargeCategoryEntity itemLargeCategoryEntity = new ItemLargeCategoryEntity();
	    
		itemLargeCategoryEntity.setName(itemLargeCategoryForm.getName());
		itemLargeCategoryRepository.save(itemLargeCategoryEntity);
	}
	
	 public List<ItemLargeCategoryEntity> getAllItemLargeCategory() {
	        // adminRepositoryのfindAll()メソッドを使ってadminテーブルの全データを取得
	        return itemLargeCategoryRepository.findAll();
	    }
	 public Optional<ItemLargeCategoryEntity> getItemLargeCategoryEntityById(Long id) {
	    	
		   return itemLargeCategoryRepository.findById(id);
	 }

}
