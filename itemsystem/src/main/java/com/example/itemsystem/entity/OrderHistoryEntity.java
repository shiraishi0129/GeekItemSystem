package com.example.itemsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_history")

public class OrderHistoryEntity {
	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	   
	 	@ManyToOne
	    @JoinColumn(name = "admin_id", referencedColumnName = "id")
	    private AdminEntity admin;
	 
	    @ManyToOne
	    @JoinColumn(name = "shop_id", referencedColumnName = "id")
	    private ShopEntity shop;
	    
	    @ManyToOne
	    @JoinColumn(name = "item_id", referencedColumnName = "id")
	    private ItemEntity item;
	    
	    @Column(name = "number_of_orders")
	    private Long numberOfOrders;
	    
	    @Column(name = "total_amount")
	    private Long totalAmount;

	    
	    @Column(name = "created_at", nullable =false ,updatable = false)
	    private LocalDateTime created_at;
	    
	    @Column(name = "updated_at", nullable = false)
	    private LocalDateTime updated_at;
	    
	    @PrePersist
	    protected void onCreate() {
	        if (this.created_at == null) {
	            this.created_at = LocalDateTime.now();
	        }
	        if (this.updated_at == null) {
	            this.updated_at = LocalDateTime.now();
	        }
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updated_at = LocalDateTime.now();
	    }

}
