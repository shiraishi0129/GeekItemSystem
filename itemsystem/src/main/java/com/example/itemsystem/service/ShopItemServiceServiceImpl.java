package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.repository.ShopItemRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ShopItemServiceServiceImpl implements ShopItemService {
	
	@Autowired
	private ShopItemRepository shopItemRepository;

	public Optional<ShopItemEntity> findById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return shopItemRepository.findById(id);
	}

	 public List<ShopItemEntity> getShopItemsByShopId(Long shopId) {
        return shopItemRepository.findByShop_Id(shopId); // shopIdを基にShopItemを取得
    }

	@Override
	public ShopItemEntity findByShop_IdAndItem_Id(Long shopId, Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return shopItemRepository.findByShop_IdAndItem_Id(shopId,id);
	}

	@Override
	public void save(ShopItemEntity shopItem) {
		// TODO 自動生成されたメソッド・スタブ
	     shopItemRepository.save(shopItem);
	}

}
