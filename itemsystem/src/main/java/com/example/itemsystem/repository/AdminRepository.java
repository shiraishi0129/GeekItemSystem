package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long>{
	AdminEntity findByEmail(String email);
}
