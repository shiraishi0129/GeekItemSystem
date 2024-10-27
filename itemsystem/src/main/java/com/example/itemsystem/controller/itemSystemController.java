package com.example.itemsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entiry.AdminEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.service.AdminService;
import com.example.itemsystem.service.AdminServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class itemSystemController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminServiceImp adminServiceImp;

	@GetMapping("/login")
	public String login() {
		return("login");
	}
	
	@PostMapping("/login")
	public String logintop(){
		return("top");
	}

	@GetMapping("/top")
	public String top() {
		return("top");
	}
	@PostMapping("/top")
	public String toppost() {
		return("top");
	}
	
	
	@GetMapping("/admin/signup")
	public String adminsignup(Model model) {
		model.addAttribute("adminForm",new AdminForm());
		return("admin/signup");
	}
	
	@PostMapping("/admin/signup")
	public String adminsignuppost(){
		return("redirect:signup");
	}
	
	@PostMapping("/admin/signup/register")	
	public String admin(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult, Model model,HttpServletRequest request){
		
		 if (errorResult.hasErrors()) {
			 model.addAttribute("adminForm", adminForm); 
	          return "admin/signup";
	        }
	     adminService.saveAdmin(adminForm);
		 
		return("top");
	}
	
	//管理者一覧画面へポスト処理
	@PostMapping("/admin/list")
	public String adminlistpost() {
		return "redirect:list";
	}
	//管理者一覧画面でのゲット処理
	@GetMapping("/admin/list")
	public String adminlistget(Model model) {
		//adminエンティティのデータを取得する為に、サービスクラスから取得
		 List<AdminEntity> adminList = adminServiceImp.getAllAdmins();
		 System.out.println("Admin List Size: " + adminList.size()); 
		 model.addAttribute("adminList", adminList);
		return "admin/list";
		
	}
	
	 @GetMapping("/admin/edit/:id")
	    public String edit(@RequestParam("id") Long id,Model model) {
			Optional<AdminEntity> adminEntity = adminServiceImp.getAdminEntityById(id);
			   if (adminEntity.isPresent()) {
		            AdminEntity admin = adminEntity.get();
		            model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
		            model.addAttribute("admin", List.of(admin)); // adminリストを設定
		            return "admin/edit";
		        } 
			   else {
				   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
			        return "error"; // エラーページを返す
			   }
	    }

	    @PostMapping("/admin/edit/:id/update")
	    public String update(@RequestParam("id") Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("adminList", adminServiceImp.getAllAdmins());
	            return "admin/edit"; // エラーがある場合、編集画面を再表示
	        }
	        
	        Optional<AdminEntity> AdminEntity = adminServiceImp.getAdminEntityById(id);
			   if (AdminEntity.isPresent()) {
				   adminServiceImp.updateAdmin(adminForm);
		        } 
			   else {
				   
			   }
			   model.addAttribute("adminentity", adminForm);
	        return "redirect:/admin/list"; // 更新後に一覧画面にリダイレクト
	    }
	 @GetMapping("/admin/detail/:id")
	 	public String showAdminDetails(@RequestParam("id") Long id, Model model) {
	   	Optional<AdminEntity> adminEntity = adminServiceImp.getAdminEntityById(id);
	   		if (adminEntity.isPresent()) {
		        AdminEntity admin = adminEntity.get();
		        model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
		        model.addAttribute("admin", List.of(admin)); // adminリストを設定
		        return "admin/detail";
	   		} 
	   		else {
			   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
		        return "error"; // エラーページを返す
		   }
	 }
	    
	 @PostMapping("/admin/delete/:id")
	public String deleteAdmin(@RequestParam("id") Long id, Model model){
		 adminServiceImp.deleteAdmin(id);
		    return "redirect:/admin/list";
	 }   
	
	//ログアウト
	@PostMapping("logout")
	public String logout() {
		return("login");
	}
}
