package com.example.itemsystem.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManufacturesForn implements Serializable{
	
	private Long id;
	
	@NotBlank
	private String Name;

}
