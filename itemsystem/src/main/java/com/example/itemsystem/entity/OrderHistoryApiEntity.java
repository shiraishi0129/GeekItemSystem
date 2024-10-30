
	package com.example.itemsystem.entity;

	import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

	@Entity
	public class OrderHistoryApiEntity {
		  @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;

		    private String productNname;
		    private String managerName;
		    private int orderQuantity;
		    private int totalAmount;

	}

