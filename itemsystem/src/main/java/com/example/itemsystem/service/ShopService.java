package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.form.ShopForm;

import jakarta.validation.Valid;

public interface ShopService {
	void saveShop(ShopForm shopForm);

	List<ShopEntity> getAllShop();

	Optional<ShopEntity> getShopEntityById(Long id);

	void updateAdmin(@Valid ShopForm shopForm);

	void deleteShop(Long id);
}
