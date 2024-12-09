package com.example.itemsystem.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.dto.CategoryApiDto;
import com.example.itemsystem.entity.OrderHistoryEntity;
import com.example.itemsystem.entity.ShopItemEntity;

public interface OrderHistoryApiRepository extends JpaRepository<OrderHistoryEntity, Long>{

	Collection<CategoryApiDto> findByItem(ShopItemEntity shopItem);

	List<OrderHistoryEntity> findByShopIdAndItemId(Long shopId, Long itemId);
}
