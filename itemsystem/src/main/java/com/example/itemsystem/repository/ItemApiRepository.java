package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemApiEntity;

public interface ItemApiRepository  extends JpaRepository<ItemApiEntity, Long>{

}
