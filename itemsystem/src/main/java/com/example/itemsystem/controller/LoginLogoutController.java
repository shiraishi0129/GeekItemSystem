package com.example.itemsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginLogoutController {


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
	
	
	//ログアウト
	@PostMapping("logout")
	public String logout() {
		return("login");
	}
}
