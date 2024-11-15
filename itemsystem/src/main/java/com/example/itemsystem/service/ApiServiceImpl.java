package com.example.itemsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.dto.CategoryApiDto;
import com.example.itemsystem.dto.ItemApiDto;
import com.example.itemsystem.dto.ItemDetailDto;
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

		    // 店舗情報を設定
		    dto.setId(shopItem.getShop().getId());
		    dto.setShopname(shopItem.getShop().getName());
		    dto.setAddress(shopItem.getShop().getAddres());

		    // 商品情報をネストして設定
		    ItemDetailDto itemDetail = new ItemDetailDto();
		    itemDetail.setItem(shopItem.getItem().getName());
		    itemDetail.setPrice(shopItem.getSalePrice());
		    itemDetail.setStock(shopItem.getQuantityOfStock());
		    dto.setItem(itemDetail);

		    // 商品発注履歴を取得してセット
		    List<OrderHistoryApiDto> orderHistoryDtos = orderHistoryRepository.findByShopIdAndItemId(
		        shopItem.getShop().getId(), shopItem.getItem().getId()
		    ).stream()
		     .map(this::convertToOrderHistoryDto)
		     .collect(Collectors.toList());
		    dto.setOrderHistories(orderHistoryDtos);

		    return dto;
		}

	 private OrderHistoryApiDto convertToOrderHistoryDto(OrderHistoryEntity orderHistory) {
		    OrderHistoryApiDto dto = new OrderHistoryApiDto();

		    dto.setId(orderHistory.getId());
		    dto.setProductName(orderHistory.getItem().getName()); // プロパティ名を修正
		    dto.setManagerName(orderHistory.getAdmin().getLastName());
		    dto.setOrderQuantity(orderHistory.getNumberOfOrders());
		    dto.setTotalAmount(orderHistory.getTotalAmount());

		    return dto;
		}


}
