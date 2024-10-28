package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.PermissionEntity;


public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

}
