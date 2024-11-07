package com.example.itemsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemsystem.dto.CategoryApiDto;
import com.example.itemsystem.dto.ItemApiDto;
import com.example.itemsystem.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiContoller {
	@Autowired
    private ApiService apiService;

//api
	
    @GetMapping("/category")
    public List<CategoryApiDto> getCategories() {
        return apiService.getCategories();
    }

    @GetMapping("/item")
    public List<ItemApiDto> getItems() {
        return apiService.getItems();
    }
}
