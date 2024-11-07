package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemEntity;

public interface CategoryApiRepository extends JpaRepository<ItemEntity,Long>{

}
