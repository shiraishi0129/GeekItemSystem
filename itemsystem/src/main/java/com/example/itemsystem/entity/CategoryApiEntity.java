package com.example.itemsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoryApiEntity {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String largeCategoryName;
	    private String underCategoryName;
	    private String smallCategoryName;
	    private String itemName;
	    private String description;
	    private double purchasePrice;
	    private double retailPrice;

}
