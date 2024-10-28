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
@Table(name = "admin")
public class AdminEntity {
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	   
	    @ManyToOne
	    @JoinColumn(name = "shop_id", referencedColumnName = "id")
	    private ShopEntity shop;
	   
	    @Column(name = "last_name", nullable = false)
	    private String lastName;

	    @Column(name = "first_name", nullable = false)
	    private String firstName;

	    @Column(name = "email", nullable = false)
	    private String email;
	    
	    @ManyToOne
	    @JoinColumn(name = "role_id", referencedColumnName = "id")
	    private RoleEntity role;

	    @ManyToOne
	    @JoinColumn(name = "permission_id", referencedColumnName = "id")
	    private PermissionEntity permission;

	    @Column(name = "phone_number", nullable = false)
	    private String phoneNumber;

	    @Column(name = "password", nullable = false)
	    private String password;
	    
	    public Long getShopId() {
	        return (shop != null) ? shop.getId() : null;
	    }

	    public Long getRoleId() {
	        return (role != null) ? role.getId() : null;
	    }

	    public Long getPermissionId() {
	        return (permission != null) ? permission.getId() : null;
	    }
	    
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
