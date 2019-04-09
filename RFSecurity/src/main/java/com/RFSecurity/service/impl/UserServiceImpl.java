package com.RFSecurity.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RFData.dao.IBaseDao;
import com.RFData.service.impl.BaseServiceImpl;
import com.RFSecurity.constants.IConstantsSecurity;
import com.RFSecurity.dao.IUserDao;
import com.RFSecurity.entities.User;
import com.RFSecurity.service.IUserService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsSecurity.SERVICE_USER)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserDetailsService, IUserService {

	@Autowired
	private IUserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.getDao().findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	@Override
	public IUserDao getDao() {
		return this.dao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setDao(IBaseDao dao) {
		this.dao = (IUserDao) dao;
	}

	/**
	 * Method to get autorities from user
	 * 
	 * @param user
	 * @return
	 */
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
