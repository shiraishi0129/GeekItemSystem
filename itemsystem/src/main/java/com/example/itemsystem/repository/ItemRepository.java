package com.example.itemsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	List<ItemEntity> findByNameContaining(String name);
	
	

}
