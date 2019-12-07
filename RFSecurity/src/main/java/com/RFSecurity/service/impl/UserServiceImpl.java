package com.RFSecurity.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RFData.service.impl.BaseServiceImpl;
import com.RFSecurity.beans.RFUserDetails;
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
public class UserServiceImpl extends BaseServiceImpl<IUserDao, User, Integer>
		implements UserDetailsService, IUserService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.getDao().findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new RFUserDetails(user.getNick(), user.getPassword(), getAuthority(user), user.getId());
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
