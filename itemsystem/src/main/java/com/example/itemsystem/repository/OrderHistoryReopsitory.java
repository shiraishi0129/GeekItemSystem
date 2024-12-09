package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.OrderHistoryEntity;

public interface OrderHistoryReopsitory  extends JpaRepository<OrderHistoryEntity, Long>{



}
