package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.entity.PermissionEntity;
import com.example.itemsystem.entity.RoleEntity;
import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.repository.AdminRepository;
import com.example.itemsystem.repository.PermissionRepository;
import com.example.itemsystem.repository.RoleRepository;
import com.example.itemsystem.repository.ShopRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveAdmin(AdminForm adminForm) {

		    AdminEntity adminEntity = new AdminEntity();
		    
		    adminEntity.setId(adminForm.getId());
		    ShopEntity shopEntity = shopRepository.findById(adminForm.getShopId())
		        .orElseThrow(() -> new RuntimeException("Shop not found"));
		    adminEntity.setShop(shopEntity);  // shopを設定

		    RoleEntity roleEntity = roleRepository.findById(adminForm.getRoleId())
		        .orElseThrow(() -> new RuntimeException("Role not found"));
		    adminEntity.setRole(roleEntity);  // roleを設定

		    PermissionEntity permissionEntity = permissionRepository.findById(adminForm.getPermissionId())
		        .orElseThrow(() -> new RuntimeException("Permission not found"));
		    adminEntity.setPermission(permissionEntity);  // permissionを設定
	
		    adminEntity.setLastName(adminForm.getLastName());
		    adminEntity.setFirstName(adminForm.getFirstName());
		    adminEntity.setEmail(adminForm.getEmail());
		    adminEntity.setPhoneNumber(adminForm.getPhoneNumber());
		    adminEntity.setPassword(passwordEncoder.encode(adminForm.getPassword()));

		    adminRepository.save(adminEntity);
	}
	
    public List<AdminEntity> getAllAdmins() {
    	return adminRepository.findAll();
    }
    
    public AdminEntity findById(Long id) {
    	return adminRepository.findById(id).orElse(null); 
    }
    
    public Optional<AdminEntity> getAdminEntityById(Long id) {
    	
    	return adminRepository.findById(id);
	   }
    
    public void updateAdmin(AdminForm adminForm) {

	    AdminEntity adminEntity = new AdminEntity();

	    adminEntity.setId(adminForm.getId());
	    ShopEntity shopEntity = shopRepository.findById(adminForm.getShopId())
	        .orElseThrow(() -> new RuntimeException("Shop not found"));
	    adminEntity.setShop(shopEntity);  // shopを設定

	    RoleEntity roleEntity = roleRepository.findById(adminForm.getRoleId())
	        .orElseThrow(() -> new RuntimeException("Role not found"));
	    adminEntity.setRole(roleEntity);  // roleを設定

	    PermissionEntity permissionEntity = permissionRepository.findById(adminForm.getPermissionId())
	        .orElseThrow(() -> new RuntimeException("Permission not found"));
	    adminEntity.setPermission(permissionEntity);  // permissionを設定

	    adminEntity.setLastName(adminForm.getLastName());
	    adminEntity.setFirstName(adminForm.getFirstName());
	    adminEntity.setEmail(adminForm.getEmail());
	    adminEntity.setPhoneNumber(adminForm.getPhoneNumber());
	    adminEntity.setPassword(passwordEncoder.encode(adminForm.getPassword()));

	    adminRepository.save(adminEntity);
    }
    
    @Transactional
    public void deleteAdmin(Long id) {
    	adminRepository.deleteById(id);
    }
    
    public AdminEntity getLoggedInAdmin(String email) {
    	return adminRepository.findByEmail(email);
    }

	@Override
	public AdminEntity findByEmail(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return adminRepository.findByEmail(name);
	}
}
