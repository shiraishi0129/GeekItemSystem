package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.form.AdminForm;

import jakarta.validation.Valid;

public interface AdminService {
	void saveAdmin(AdminForm adminForm);

	AdminEntity getLoggedInAdmin(String email);

	List<AdminEntity> getAllAdmins();

	Optional<AdminEntity> getAdminEntityById(Long id);

	void updateAdmin(@Valid AdminForm adminForm);

	void deleteAdmin(Long id);
}
