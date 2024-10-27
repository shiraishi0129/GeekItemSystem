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
import com.example.itemsystem.entiry.ManufacturesEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.form.ManufacturesForn;
import com.example.itemsystem.service.AdminService;
import com.example.itemsystem.service.AdminServiceImpl;

import com.example.itemsystem.service.ManufactureServiceImpl;
import com.example.itemsystem.service.ManufacturesService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class itemSystemController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminServiceImpl adminServiceImp;
<<<<<<< HEAD
	@Autowired
	private ManufacturesService manufacturesService;
	@Autowired
	private ManufactureServiceImpl manufacturesServiceImpl;
=======
>>>>>>> c597a9dc7df2f7403472c427a933c25d155b6689

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
	
	
	
	//管理者情報処理↓
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
	    public String adminedit(@RequestParam("id") Long id,Model model) {
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
	    public String adminupdate(@RequestParam("id") Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
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
	 //管理者情報の処理↑
	
	 
	 
	 
	 //メーカ情報の処理
		//管理者一覧画面へポスト処理
		@PostMapping("/manufactures/list")
		public String manufactureslistpost() {
			return "redirect:list";
		}
		//管理者一覧画面でのゲット処理
		@GetMapping("/manufactures/list")
		public String manufactureslistget(Model model) {
			//manufacturesエンティティのデータを取得する為に、サービスクラスから取得
			 List<ManufacturesEntity> manufacturesList = manufacturesServiceImpl.getAllManufactures();
			 System.out.println("Manufactures List Size: " + manufacturesList.size()); 
			 model.addAttribute("manufacturesList", manufacturesList);
			return "/manufactures/list";
			
		}
		
		@GetMapping("/manufactures/signup")
		public String manufacturessignup(Model model) {
			model.addAttribute("manufacturesForm",new ManufacturesForn());
			return("manufactures/signup");
		}
		
		@PostMapping("/manufactures/signup")
		public String manufacturessignuppost(){
			return("redirect:signup");
		}
		@PostMapping("/manufactures/signup/register")	
		public String manufactures(@Validated @ModelAttribute("manufacturesForm") ManufacturesForn manufacturesForm, BindingResult errorResult, Model model,HttpServletRequest request){
			
			 if (errorResult.hasErrors()) {
				 model.addAttribute("manufacturesForm", manufacturesForm); 
		          return "admin/signup";
		        }
		     manufacturesService.saveManufactures(manufacturesForm);
			 
			return("top");
			
		}
		
		 @GetMapping("/manufactures/edit/:id")
		    public String manufacturesedit(@RequestParam("id") Long id,Model model) {
				Optional<ManufacturesEntity> manufactuersEntity = manufacturesServiceImpl.getManufacturesEntityById(id);
				   if (manufactuersEntity.isPresent()) {
			            ManufacturesEntity manufactures = manufactuersEntity.get();
			            model.addAttribute("manufacturesForm", manufactures);
			            model.addAttribute("manufactures", List.of(manufactures)); 
			            return "manufactures/edit";
			        } 
				   else {
					   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
				        return "error"; // エラーページを返す
				   }
		    }
		 
		 @PostMapping("/manufactures/edit/:id/update")
		    public String manufacturesupdate(@RequestParam("id") Long id, @Valid @ModelAttribute ManufacturesForn manufacturesForm, BindingResult bindingResult, Model model) {
		        if (bindingResult.hasErrors()) {
		            model.addAttribute("manufacturesList", manufacturesServiceImpl.getAllManufactures());
		            return "admin/edit"; // エラーがある場合、編集画面を再表示
		        }
		        
		        Optional<ManufacturesEntity> ManufacturesEntity = manufacturesServiceImpl.getManufacturesEntityById(id);
				   if (ManufacturesEntity.isPresent()) {
					   manufacturesServiceImpl.updateManufactures(manufacturesForm);
			        } 
				   else {
					   
				   }
				   model.addAttribute("manufacturesentity", manufacturesForm);
		        return "redirect:/manufactures/list"; // 更新後に一覧画面にリダイレクト
		    }
	 //
	 
	//ログアウト
	@PostMapping("logout")
	public String logout() {
		return("login");
	}
}
