package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entiry.PermissionEntity;


public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

}
