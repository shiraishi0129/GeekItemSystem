package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.RoleEntity;

public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {

}
