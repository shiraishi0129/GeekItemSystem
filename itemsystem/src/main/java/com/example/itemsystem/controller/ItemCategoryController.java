package com.example.itemsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.ItemLargeCategoryEntity;
import com.example.itemsystem.entity.ItemSmallCategoryEntity;
import com.example.itemsystem.entity.ItemUnderCategoryEntity;
import com.example.itemsystem.service.ItemLargeCategoryService;
import com.example.itemsystem.service.ItemSmallCategoryService;
import com.example.itemsystem.service.ItemUnderCategoryService;


@Controller
public class ItemCategoryController {
	
    @Autowired
    private ItemLargeCategoryService itemLargeCategoryService;
    @Autowired
    private ItemUnderCategoryService itemUnderCategoryService;
    @Autowired
    private ItemSmallCategoryService itemSmallCategoryService;
	
	@PostMapping("/category/list")
    public String categorylistpost() {
        return "redirect:list";
    }

    // 管理者一覧画面でのゲット処理
    @GetMapping("/category/list")
    public String categorylistget(Model model) {
        // ItemLargeCategoryエンティティのデータを取得する為に、サービスクラスから取得
        List<ItemLargeCategoryEntity> itemLargeCategoryList = itemLargeCategoryService.getAllItemLargeCategory();
        System.out.println("Manufactures List Size: " + itemLargeCategoryList.size());
        model.addAttribute("itemLargeCategoryList", itemLargeCategoryList);
        return "/category/list";
    }

    @GetMapping("/category/bigdetail/:id")
    public String showCategoryDetails(@RequestParam("id") Long id, Model model) {
        Optional<ItemLargeCategoryEntity> itemLargeCategoryEntity = itemLargeCategoryService.getItemLargeCategoryEntityById(id);
        if (itemLargeCategoryEntity.isPresent()) {
            ItemLargeCategoryEntity itemLargeCategory = itemLargeCategoryEntity.get(); 
            List<ItemUnderCategoryEntity> itemUnderCategory = itemLargeCategoryService.getUnderCategoriesByLargeId(id);
            model.addAttribute("itemLargeCategory", List.of(itemLargeCategory)); 
            model.addAttribute("underCategory", itemUnderCategory);
            return "category/bigdetail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }
    
    @GetMapping("/category/underdetail/:id")
    public String showUnderCategoryDetails(@RequestParam("id") Long id, Model model) {
        Optional<ItemUnderCategoryEntity> itemUnderCategoryEntity = itemUnderCategoryService.getItemUnderCategoryEntityById(id);
        if (itemUnderCategoryEntity.isPresent()) {
            ItemUnderCategoryEntity itemUnderCategory = itemUnderCategoryEntity.get(); 
            List<ItemSmallCategoryEntity> itemSmallCategory = itemUnderCategoryService.getSmallCategoriesByUnderId(id);
            model.addAttribute("itemUnderCategory", List.of(itemUnderCategory)); 
            model.addAttribute("smallCategory", itemSmallCategory);
            return "category/underdetail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }
    
    @GetMapping("/category/smalldetail/:id")
    public String showSmallCategoryDetails(@RequestParam("id") Long id, Model model) {
        Optional<ItemSmallCategoryEntity> itemSmallCategoryEntity = itemSmallCategoryService.getItemSmallCategoryEntityById(id);
        if (itemSmallCategoryEntity.isPresent()) {
            ItemSmallCategoryEntity itemSmallCategory = itemSmallCategoryEntity.get(); 
            model.addAttribute("itemSmallCategory", List.of(itemSmallCategory)); 
            return "category/smalldetail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }
}
