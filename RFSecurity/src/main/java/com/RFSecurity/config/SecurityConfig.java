package com.RFSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.RFSecurity.dao.IUserDao;
import com.RFSecurity.dao.impl.UserDaoImpl;
import com.RFSecurity.service.IUserService;
import com.RFSecurity.service.impl.UserServiceImpl;

@Configuration
public class SecurityConfig {
	@Bean
	public IUserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public IUserDao userDao() {
		return new UserDaoImpl();
	}
	
	@Bean
	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint();
	}
	
	@Bean
	public TokenProvider tokenProvider() {
		return new TokenProvider();
	}
}
