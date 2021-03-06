package com.RFSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFSecurity.filters.JwtAuthenticationFilter;

/**
 * 
 * @author diego
 *
 */
public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	private boolean enableCors = true;
	private boolean crsfDisabled = true;

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		this.defaultConfiguration(http);
	}

	/**
	 * Method to generate default configuration for HttpSecurty
	 * 
	 * @param http
	 * @throws Exception
	 */
	public void defaultConfiguration(HttpSecurity http) throws Exception {
		if (this.isEnableCors()) {
			http.cors();
		}
		if (this.isCrsfDisabled()) {
			http.csrf().disable();
		}
		this.authorizeRequestsPermitAll(http);
		this.authorizeRequestsAuthenticated(http);
		this.sessionCreationPolicy(http);
		this.addFiltersBefore(http);
	}

	/**
	 * Method to configure urls permit all
	 * 
	 * @param http
	 * @throws Exception
	 */
	public void authorizeRequestsPermitAll(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(IConstantsSecurity.URL_AUTH, IConstantsSecurity.URL_SIGNUP).permitAll();
	}

	/**
	 * Method to configure urls authenticated
	 * 
	 * @param http
	 * @throws Exception
	 */
	public void authorizeRequestsAuthenticated(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler);
	}

	/**
	 * Method to configure createion session policy
	 * 
	 * @param http
	 * @throws Exception
	 */
	public void sessionCreationPolicy(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * Method to add filter before http creation
	 * 
	 * @param http
	 * @throws Exception
	 */
	public void addFiltersBefore(HttpSecurity http) throws Exception {
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	public boolean isEnableCors() {
		return enableCors;
	}

	public void setEnableCors(boolean enableCors) {
		this.enableCors = enableCors;
	}

	public boolean isCrsfDisabled() {
		return crsfDisabled;
	}

	public void setCrsfDisabled(boolean crsfDisabled) {
		this.crsfDisabled = crsfDisabled;
	}

}