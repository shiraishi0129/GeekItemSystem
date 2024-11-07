package com.example.itemsystem.service;

import java.util.List;

import com.example.itemsystem.dto.CategoryApiDto;
import com.example.itemsystem.dto.ItemApiDto;

public interface ApiService {

	List<CategoryApiDto> getCategories();

	List<ItemApiDto> getItems();


}
