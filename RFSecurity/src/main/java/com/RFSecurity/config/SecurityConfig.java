package com.RFSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
//	@Bean
//	public IUserService userService() {
//		return new UserServiceImpl();
//	}
//	
//	@Bean
//	public IUserDao userDao() {
//		return new UserDaoImpl();
//	}
//	
//	@Bean
//	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
//		return new JwtAuthenticationEntryPoint();
//	}
//	
	@Bean
	public TokenProvider tokenProvider() {
		return new TokenProvider();
	}
}
