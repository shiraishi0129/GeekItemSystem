package com.example.itemsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ItemApiEntity;
import com.example.itemsystem.repository.ItemApiRepository;

@Service
public class ItemApiServiceImpl implements ItemApiService {
    @Autowired
    private ItemApiRepository itemApiRepository;

    public List<ItemApiEntity> getAllItems() {
        return itemApiRepository.findAll();
    }
}
