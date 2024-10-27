package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entiry.RoleEntity;

public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {

}
