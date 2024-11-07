package com.example.itemsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.dto.CategoryApiDto;
import com.example.itemsystem.dto.ItemApiDto;
import com.example.itemsystem.dto.OrderHistoryApiDto;
import com.example.itemsystem.entity.ItemEntity;
import com.example.itemsystem.entity.OrderHistoryEntity;
import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.repository.CategoryApiRepository;
import com.example.itemsystem.repository.ItemApiRepository;
import com.example.itemsystem.repository.OrderHistoryApiRepository;

@Service
public class ApiServiceImpl implements ApiService {

	
	@Autowired
    private CategoryApiRepository categoryApiRepository;
    @Autowired
    private ItemApiRepository itemApiRepository;
    @Autowired
    private OrderHistoryApiRepository orderHistoryRepository;

    public List<CategoryApiDto> getCategories() {
        return categoryApiRepository.findAll().stream()
                .map(this::convertToCategoryDto)
                .collect(Collectors.toList());
    }

    public List<ItemApiDto> getItems() {
        return itemApiRepository.findAll().stream()
                .map(this::convertToItemDto)
                .collect(Collectors.toList());
    }
    
    public List<OrderHistoryApiDto> getOrderHistory() {
        return orderHistoryRepository.findAll().stream()
                .map(this::convertToOrderHistoryDto)
                .collect(Collectors.toList());
    }
    
	 private CategoryApiDto convertToCategoryDto(ItemEntity item) {
	        CategoryApiDto dto = new CategoryApiDto();
	        dto.setId(item.getId());
	        dto.setItemName(item.getName());
	        dto.setDescription(item.getProductDescription());
	        dto.setPurchasePrice(item.getPurchasePrice());
	        dto.setRetailPrice(item.getRetailPrice());
	        
	        // Small Category (孫カテゴリ)
	        if (item.getItemSmallCategory() != null) {
	            dto.setSmallCategoryName(item.getItemSmallCategory().getSmallCategoryName());
	        }

	        // Under Category (子カテゴリ) と Large Category (親カテゴリ) を参照する場合
	        if (item.getItemSmallCategory() != null && item.getItemSmallCategory().getItemUnderCategory() != null) {
	            dto.setUnderCategoryName(item.getItemSmallCategory().getItemUnderCategory().getUnderCategoryName());

	            // Large Category (親カテゴリ) も参照する
	            if (item.getItemSmallCategory().getItemUnderCategory().getItemLargeCategory() != null) {
	                dto.setLargeCategoryName(item.getItemSmallCategory().getItemUnderCategory().getItemLargeCategory().getLargeCategoryName());
	            }
	        }
	        return dto;
	    }
	 
    private ItemApiDto convertToItemDto(ShopItemEntity shopItem) {
    	 ItemApiDto dto = new ItemApiDto();
    	 dto.setId(shopItem.getId());
         dto.setShopName(shopItem.getShop().getName());
         dto.setAddress(shopItem.getShop().getAddres());
         dto.setItem(shopItem.getItem().getName());
         dto.setPrice(shopItem.getSalePrice());
         dto.setStock(shopItem.getQuantityOfStock());
         
      

         // 注文履歴を取得してセット
         List<OrderHistoryApiDto> orderHistoryDtos = orderHistoryRepository.findByShopIdAndItemId(shopItem.getShop().getId(),shopItem.getItem().getId()).stream()
                 .map(this::convertToOrderHistoryDto)
                 .collect(Collectors.toList());
         dto.setOrderHistories(orderHistoryDtos);

        return dto;
    }
    
    private OrderHistoryApiDto convertToOrderHistoryDto(OrderHistoryEntity orderHistory) {
        OrderHistoryApiDto dto = new OrderHistoryApiDto();
        dto.setId(orderHistory.getId());
        dto.setProductNname(orderHistory.getItem().getName());
        dto.setManagerName(orderHistory.getAdmin().getLastName());
        dto.setOrderQuantity(orderHistory.getNumberOfOrders());
        dto.setTotalAmount(orderHistory.getTotalAmount());
        return dto;
    }

}
