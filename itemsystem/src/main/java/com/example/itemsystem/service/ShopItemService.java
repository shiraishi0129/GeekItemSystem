package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ShopItemEntity;

public interface ShopItemService {

	Optional<ShopItemEntity> findById(Long id);

	List<ShopItemEntity> getShopItemsByShopId(Long id);

}
