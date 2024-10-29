package com.example.itemsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.ItemEntity;
import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.entity.ShopItemEntity; 

public interface ShopItemRepository extends JpaRepository<ShopItemEntity, Long>{
	Optional<ShopItemEntity> findByShopAndItem(ShopEntity shop, ItemEntity item);

	ShopItemEntity  findByShop_IdAndItem_Id(Long shopId, Long id);
	


}
