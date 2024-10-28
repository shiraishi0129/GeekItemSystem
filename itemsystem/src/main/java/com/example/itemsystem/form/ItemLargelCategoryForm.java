package com.example.itemsystem.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemLargelCategoryForm {
	
	private Long id;
	
	@NotBlank
	private String Name;

}
