package com.example.itemsystem.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminForm implements Serializable{
	
	private Long id;
	
	@NotBlank
	private String lastName;
	
	@Min(1)
	@NotNull
	private Long shopId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	@Email
	private String email;
	
	@Min(1)
	@NotNull
	private Long roleId;
	
	@Min(1)
	@NotNull
	private Long permissionId;
	
	@NotBlank
	@Size(min = 10, max = 11)
	private String phoneNumber;
	
	@NotBlank
	private String password;
}
