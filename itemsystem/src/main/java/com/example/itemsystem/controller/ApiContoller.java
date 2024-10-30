package com.example.itemsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemsystem.entity.CategoryApiEntity;
import com.example.itemsystem.entity.ItemApiEntity;
import com.example.itemsystem.service.CategoryApiServiceImpl;
import com.example.itemsystem.service.ItemApiServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiContoller {
	@Autowired
    private CategoryApiServiceImpl categoryApiServiceImpl;
    @Autowired
    private ItemApiServiceImpl itemApiServiceImpl;

    
    

//api
    @GetMapping("/catgory")
    public List<CategoryApiEntity> getCategories() {
        return categoryApiServiceImpl.getAllCategories();
    }

    @GetMapping("/item")
    public List<ItemApiEntity> getItems() {
        return itemApiServiceImpl.getAllItems();
    }
//
    

}
