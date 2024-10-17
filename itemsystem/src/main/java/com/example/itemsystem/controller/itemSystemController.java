package com.example.itemsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class itemSystemController {

	@GetMapping("/login")
	public String login() {
		return("login");
	}
	

	@GetMapping("/top")
	public String top() {
		return("top");
	}
	
	@GetMapping("/admin/signup")
	public String adminsignup() {
		return("admin/signup");
	}
}
