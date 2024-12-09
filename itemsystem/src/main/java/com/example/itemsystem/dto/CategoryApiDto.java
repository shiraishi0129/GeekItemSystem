package com.example.itemsystem.dto;

import lombok.Data;

@Data
public class CategoryApiDto {
		
	private Long id;
	private String largeCategoryName;
	private String underCategoryName;
	private String smallCategoryName;
	private String itemName;
	private String description;
	private Long purchasePrice;
	private Long retailPrice;
}
