package com.example.itemsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.service.AdminService;

import jakarta.validation.Valid;

@Controller
public class AdminProfileController {
	
	 @Autowired
	 private AdminService adminService;
	 
	@PostMapping("/adminprofile/detail")
    public String adminprofiledetailpost() {
        return "redirect:detail";
    }

    @GetMapping("/adminprofile/detail")
    public String showProfile(Model model, Principal principal) {
        // ログイン中の管理者のメールアドレスを取得
        String email = principal.getName();

        // 管理者情報を取得
        AdminEntity admin = adminService.getLoggedInAdmin(email);

        // モデルに追加してHTMLに渡す
        model.addAttribute("admin", admin);

        return "adminprofile/detail"; // dashboard.htmlに遷移
    }

    @PostMapping("/adminprofile/edit")
    public String adminprofileeditpost() {
        return "redirect:edit";
    }

    @GetMapping("/adminprofile/edit/{id}")
    public String adminprofileedit(@PathVariable Long id, Model model) {
        Optional<AdminEntity> adminEntity = adminService.getAdminEntityById(id);
        if (adminEntity.isPresent()) {
            AdminEntity admin = adminEntity.get();
            model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
            model.addAttribute("admin", List.of(admin)); // adminリストを設定
            return "adminprofile/edit";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

    @PostMapping("/adminprofile/edit/{id}/update")
    public String adminprofileupdate(@PathVariable Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminList", adminService.getAllAdmins());
            return "adminprofile/edit"; // エラーがある場合、編集画面を再表示
        }

        Optional<AdminEntity> AdminEntity = adminService.getAdminEntityById(id);
        if (AdminEntity.isPresent()) {
            adminService.updateAdmin(adminForm);
        } else {
            // Handle error
        }
        model.addAttribute("adminentity", adminForm);
        return "redirect:/top"; // 更新後に一覧画面にリダイレクト
    }
}
