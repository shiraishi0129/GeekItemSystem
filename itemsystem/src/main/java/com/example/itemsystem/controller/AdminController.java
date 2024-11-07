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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 管理者情報処理↓
    @GetMapping("/admin/signup")
    public String adminsignup(Model model) {
        model.addAttribute("adminForm", new AdminForm());
        return "admin/signup";
    }

    @PreAuthorize("hasRole('1')")
    @PostMapping("/admin/signup")
    public String adminsignuppost() {
        return "redirect:signup";
    }

    @PostMapping("/admin/signup/register")
    public String admin(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult, Model model, HttpServletRequest request) {
        if (errorResult.hasErrors()) {
            model.addAttribute("adminForm", adminForm);
            return "admin/signup";
        }
        adminService.saveAdmin(adminForm);
        return "top";
    }

    // 管理者一覧画面へポスト処理
    @PostMapping("/admin/list")
    public String adminlistpost() {
        return "redirect:list";
    }

    // 管理者一覧画面でのゲット処理
    @GetMapping("/admin/list")
    public String adminlistget(Model model) {
        // adminエンティティのデータを取得する為に、サービスクラスから取得
        List<AdminEntity> adminList = adminService.getAllAdmins();
        System.out.println("Admin List Size: " + adminList.size());
        model.addAttribute("adminList", adminList);
        return "admin/list";
    }

    @PreAuthorize("hasRole('1')")
    @GetMapping("/admin/edit/:id")
    public String adminedit(@RequestParam("id") Long id, Model model) {
        Optional<AdminEntity> adminEntity = adminService.getAdminEntityById(id);
        if (adminEntity.isPresent()) {
            AdminEntity admin = adminEntity.get();
            model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
            model.addAttribute("admin", List.of(admin)); // adminリストを設定
            return "admin/edit";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

    @PreAuthorize("hasRole('1')")
    @PostMapping("/admin/edit/:id/update")
    public String adminupdate(@RequestParam("id") Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminList", adminService.getAllAdmins());
            return "admin/edit"; // エラーがある場合、編集画面を再表示
        }

        Optional<AdminEntity> adminEntity = adminService.getAdminEntityById(id);
        if (adminEntity.isPresent()) {
            adminService.updateAdmin(adminForm);
        } else {
            // エラーハンドリング処理を追加できます
        }
        model.addAttribute("adminentity", adminForm);
        return "redirect:/admin/list"; // 更新後に一覧画面にリダイレクト
    }

    @GetMapping("/admin/detail/{id}")
    public String showAdminDetails(@PathVariable Long id, Model model) {
        Optional<AdminEntity> adminEntity = adminService.getAdminEntityById(id);
        if (adminEntity.isPresent()) {
            AdminEntity admin = adminEntity.get();
            model.addAttribute("admin", admin); // `contact`オブジェクトを渡す
            return "admin/detail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

    @PreAuthorize("hasRole('1')")
    @PostMapping("/admin/delete/:id")
    public String deleteAdmin(@RequestParam("id") Long id, Model model) {
        adminService.deleteAdmin(id);
        return "redirect:/admin/list";
    }
    // 管理者情報の処理↑
}
