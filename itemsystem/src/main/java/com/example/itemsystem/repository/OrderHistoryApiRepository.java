package com.example.itemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemsystem.entity.OrderHistoryApiEntity;

public interface OrderHistoryApiRepository extends JpaRepository<OrderHistoryApiEntity, Long>{

}
