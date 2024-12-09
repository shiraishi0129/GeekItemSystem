package com.example.itemsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemSmallCategoryEntity;

public interface ItemSmallCategoryRepository extends JpaRepository<ItemSmallCategoryEntity, Long>{

	List<ItemSmallCategoryEntity> findByitemUnderCategory_Id(Long id);



}
