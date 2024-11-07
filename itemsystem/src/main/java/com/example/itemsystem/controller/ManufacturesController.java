package com.example.itemsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.ItemLargeCategoryEntity;
import com.example.itemsystem.entity.ManufacturesEntity;
import com.example.itemsystem.form.ManufacturesForn;
import com.example.itemsystem.service.ItemLargeCategoryService;
import com.example.itemsystem.service.ManufactureService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@Controller
public class ManufacturesController {

    @Autowired
    private ManufactureService manufacturesService;
    @Autowired
    private ItemLargeCategoryService itemLargeCategoryService;

    // メーカ情報の処理
    // メーカ一覧画面へポスト処理
    @PostMapping("/manufactures/list")
    public String manufactureslistpost() {
        return "redirect:list";
    }

    // メーカ一覧画面でのゲット処理
    @GetMapping("/manufactures/list")
    public String manufactureslistget(Model model) {
        // manufacturesエンティティのデータを取得する為に、サービスクラスから取得
        List<ManufacturesEntity> manufacturesList = manufacturesService.getAllManufactures();
        System.out.println("Manufactures List Size: " + manufacturesList.size());
        model.addAttribute("manufacturesList", manufacturesList);
        return "/manufactures/list";
    }

    @GetMapping("/manufactures/signup")
    public String manufacturessignup(Model model) {
        model.addAttribute("manufacturesForm", new ManufacturesForn());
        return "manufactures/signup";
    }

    @PreAuthorize("hasRole('1')")
    @PostMapping("/manufactures/signup")
    public String manufacturessignuppost() {
        return "redirect:signup";
    }

    @PostMapping("/manufactures/signup/register")
    public String manufactures(@Validated @ModelAttribute("manufacturesForm") ManufacturesForn manufacturesForm,
                               BindingResult errorResult, Model model, HttpServletRequest request) {

        if (errorResult.hasErrors()) {
            model.addAttribute("manufacturesForm", manufacturesForm);
            return "admin/signup";
        }
        manufacturesService.saveManufactures(manufacturesForm);

        return "top";
    }

    @PreAuthorize("hasRole('1')")
    @GetMapping("/manufactures/edit/:id")
    public String manufacturesedit(@RequestParam("id") Long id, Model model) {
        Optional<ManufacturesEntity> manufactuersEntity = manufacturesService.getManufacturesEntityById(id);
        if (manufactuersEntity.isPresent()) {
            ManufacturesEntity manufactures = manufactuersEntity.get();
            model.addAttribute("manufacturesForm", manufactures);
            model.addAttribute("manufactures", List.of(manufactures));
            return "manufactures/edit";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

    @PreAuthorize("hasRole('1')")
    @PostMapping("/manufactures/edit/:id/update")
    public String manufacturesupdate(@RequestParam("id") Long id, @Valid @ModelAttribute ManufacturesForn manufacturesForm,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturesList", manufacturesService.getAllManufactures());
            return "admin/edit"; // エラーがある場合、編集画面を再表示
        }

        Optional<ManufacturesEntity> ManufacturesEntity = manufacturesService.getManufacturesEntityById(id);
        if (ManufacturesEntity.isPresent()) {
            manufacturesService.updateManufactures(manufacturesForm);
        } else {
            // Handle case where entity is not found
        }
        model.addAttribute("manufacturesentity", manufacturesForm);
        return "redirect:/manufactures/list"; // 更新後に一覧画面にリダイレクト
    }

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

    @GetMapping("/category/detail/:id")
    public String showCategoryDetails(@RequestParam("id") Long id, Model model) {
        Optional<ItemLargeCategoryEntity> itemLargeCategoryEntity = itemLargeCategoryService.getItemLargeCategoryEntityById(id);
        if (itemLargeCategoryEntity.isPresent()) {
            ItemLargeCategoryEntity itemLargeCategory = itemLargeCategoryEntity.get();
            model.addAttribute("itemLargeCategoryForm", itemLargeCategory); // `contact`オブジェクトを渡す
            model.addAttribute("itemLargeCategory", List.of(itemLargeCategory)); // adminリストを設定
            return "category/detail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

}



