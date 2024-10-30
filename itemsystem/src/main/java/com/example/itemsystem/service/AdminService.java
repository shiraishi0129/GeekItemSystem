package com.example.itemsystem.service;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.form.AdminForm;

public interface AdminService {
	void saveAdmin(AdminForm adminForm);

	AdminEntity getLoggedInAdmin(String email);
	

}
