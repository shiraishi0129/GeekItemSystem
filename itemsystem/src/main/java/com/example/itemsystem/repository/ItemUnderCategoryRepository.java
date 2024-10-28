package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entiry.ItemUnderCategoryEntity;

public interface ItemUnderCategoryRepository extends JpaRepository<ItemUnderCategoryEntity, Long>{

}
