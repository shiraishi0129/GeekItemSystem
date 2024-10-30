package com.example.itemsystem.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authz) -> authz
        		.requestMatchers("*").hasAnyRole("1","2") // ロール1にアクセスを許可
                .anyRequest().authenticated())
	        .formLogin(login -> login
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .loginPage("/login")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/top", true)
	            .permitAll())
	        .logout((logout) -> logout
	            .logoutSuccessUrl("/login")
	            .logoutUrl("/logout")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll());
	    
	    return http.build();
	}

	 @Bean
	   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();	    
     }
	 
}
