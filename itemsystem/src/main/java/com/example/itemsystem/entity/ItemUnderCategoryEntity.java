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
@Table(name = "item_under_category")
public class ItemUnderCategoryEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "item_large_category_id", referencedColumnName = "id")
	private ItemLargeCategoryEntity itemLargeCategory;
	
	@Column(name = "name")
	private String name;
	
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
	
	@OneToMany(mappedBy = "itemUnderCategory", cascade = CascadeType.ALL)
	private List<ItemSmallCategoryEntity> itemSmallCategory;
	
	public String getUnderCategoryName() {
		// TODO 自動生成されたメソッド・スタブ
		return name;
	}
}
