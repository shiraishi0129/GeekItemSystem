package com.example.itemsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.CategoryApiEntity;
import com.example.itemsystem.repository.CategoryApiRepository;

@Service
public class CategoryApiServiceImpl implements CategoryApiService {
	   @Autowired
	    private CategoryApiRepository categoryApiRepository;

	    public List<CategoryApiEntity> getAllCategories() {
	        return categoryApiRepository.findAll();
	    }
}
