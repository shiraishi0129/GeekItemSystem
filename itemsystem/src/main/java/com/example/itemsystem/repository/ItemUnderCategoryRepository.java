package com.example.itemsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemUnderCategoryEntity;

public interface ItemUnderCategoryRepository extends JpaRepository<ItemUnderCategoryEntity, Long>{

	List<ItemUnderCategoryEntity> findByitemLargeCategory_Id(Long id);
}
