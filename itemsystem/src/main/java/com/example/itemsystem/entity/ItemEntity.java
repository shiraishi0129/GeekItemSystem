package com.example.itemsystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	   
	@ManyToOne
	@JoinColumn(name = "manufactures_id", referencedColumnName = "id")
	private ManufacturesEntity manufactures;
	
	@ManyToOne
	@JoinColumn(name = "small_category_id", referencedColumnName = "id")
	private ItemSmallCategoryEntity itemSmallCategory;
	   
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "product_description", nullable = false)
	private String productDescription;
	
	@Column(name = "image", nullable = false)
	private String image;
	    
	@Column(name = "purchase_price", nullable = false)
	private Long purchasePrice;
	 
	@Column(name = "retail_price", nullable = false)
	private Long retailPrice;
	 
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
	
	@ManyToOne
	@JoinColumn(name = "large_category_id", referencedColumnName = "id")
	private ItemLargeCategoryEntity itemLargeCategory;
	
	@ManyToOne
	@JoinColumn(name = "under_category_id", referencedColumnName = "id")
	private ItemUnderCategoryEntity itemUnderCategory;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<ShopItemEntity> shopItem;
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<OrderHistoryEntity> orderHistory;
}	    
