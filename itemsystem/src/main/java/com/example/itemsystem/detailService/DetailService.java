package com.example.itemsystem.detailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entiry.AdminEntity;
import com.example.itemsystem.repository.AdminRepository;

@Service
public class DetailService implements UserDetailsService {
	
	private AdminRepository adminRepositry;
	
	@Autowired
	public void DataService(AdminRepository adminRepository,PasswordEncoder passwordEncoder) {
		this.adminRepositry = adminRepository;
		}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		AdminEntity adminentity = adminRepositry.findByEmail(email);
				if (adminentity == null) {
		            throw new UsernameNotFoundException("User not found with email: " + email);
		        }
	return User.builder()
			.username(adminentity.getEmail())
			.password(adminentity.getPassword())
			.roles("ADMIN")
			.build();

	}
}
