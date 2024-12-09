package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ShopItemEntity;

public interface ItemApiRepository  extends JpaRepository<ShopItemEntity, Long>{

}
