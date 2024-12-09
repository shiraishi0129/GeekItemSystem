package com.example.itemsystem.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemForm {

	private Long id;
	
	@Min(1)
	@NotNull
	private Long manufacturesId;
	
	@Min(1)
	@NotNull
	private Long itemSmallCategoryId;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String productDescription;
	
	@NotBlank
	private String image;
	
	@Min(1)
	@NotNull
	private Long purchasePrice;
	
	@Min(1)
	@NotNull
	private Long retailPrice;
}
