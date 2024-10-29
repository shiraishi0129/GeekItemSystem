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
@Table(name = "shop_item")
public class ShopItemEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private ShopEntity shop;
    
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemEntity item;
   
 /*  @Column(name = "sale_price", nullable = false)
    private Long salePrice;
*/
    @Column(name = "quantity_of_stock", nullable = false)
    private Long quantityOfStock;
    
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

	public void setItemId(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		this.item = new ItemEntity(); // 新しいItemEntityを作成
	    this.item.setId(id); // itemIdを設定
		
	}

	public void setShopId(Long shopId) {
		// TODO 自動生成されたメソッド・スタブ
		this.shop = new ShopEntity(); // 新しいItemEntityを作成
	    this.shop.setId(shopId); // itemIdを設定
	}
	
	public void setQuantityOfStock(Long quantityOfStock) {
	    this.quantityOfStock = quantityOfStock; // 引数の値をフィールドに設定
	}

}
