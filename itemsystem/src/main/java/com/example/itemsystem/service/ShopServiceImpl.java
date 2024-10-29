package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.form.ShopForm;
import com.example.itemsystem.repository.ShopRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Service
@Transactional
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopRepository shopRepository;

	public List<ShopEntity> getAllShop() {
		// TODO 自動生成されたメソッド・スタブ
		return shopRepository.findAll();
	}

	@Override
	public void saveShop(ShopForm shopForm) {
	    ShopEntity shopEntity = new ShopEntity();
	    
	    shopEntity.setId(shopForm.getId());
	    shopEntity.setName(shopForm.getName());
	    shopEntity.setAddres(shopForm.getAddres());

	    shopRepository.save(shopEntity);
		
	}

	   public Optional<ShopEntity> getShopEntityById(Long id) {
	    	
		   return shopRepository.findById(id);
	   }

	public void updateAdmin(@Valid ShopForm shopForm) {
		// TODO 自動生成されたメソッド・スタブ
		ShopEntity shopEntity = new ShopEntity();
	    
	    shopEntity.setId(shopForm.getId());
	    shopEntity.setName(shopForm.getName());
	    shopEntity.setAddres(shopForm.getAddres());

	    shopRepository.save(shopEntity);
	}

	public void deleteShop(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		shopRepository.deleteById(id);
	}

}
