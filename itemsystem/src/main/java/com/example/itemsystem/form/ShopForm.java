package com.example.itemsystem.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShopForm implements Serializable{
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String addres;
	
}
