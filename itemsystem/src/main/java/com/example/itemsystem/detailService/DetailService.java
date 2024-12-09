package com.example.itemsystem.detailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.repository.AdminRepository;

@Service
public class DetailService implements UserDetailsService {

    private AdminRepository adminRepositry;

    @Autowired
    public void DataService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepositry = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AdminEntity adminentity = adminRepositry.findByEmail(email);
        if (adminentity == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Long permissionId = adminentity.getPermissionId();
        System.out.println("Loaded user: " + adminentity.getEmail() + ", Permission ID: " + permissionId);

        if (permissionId != null) {
            String role = permissionId.toString(); // 直接 permissionId を文字列に変換
            System.out.println("Assigned role: " + role);

            return User.builder()
                    .username(adminentity.getEmail())
                    .password(adminentity.getPassword())
                    .roles(role) // ここでの role には "1" などの数字が入る
                    .build();
        } else {
            throw new IllegalArgumentException("Permission ID is null");
        }
    }
}
