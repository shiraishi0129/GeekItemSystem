package com.example.itemsystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shop")
public class ShopEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
   
	@Column(name = "name", nullable = false)
	private String name;
   
	@Column(name = "addres", nullable = false)
	private String addres;

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
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
	private List<ShopItemEntity> shopItem;
   
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
	private List<OrderHistoryEntity> orderHistory;
}
