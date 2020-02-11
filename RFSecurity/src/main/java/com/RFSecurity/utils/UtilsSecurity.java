package com.RFSecurity.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.RFSecurity.beans.RFUserDetails;

/**
 * 
 * @author diego
 *
 */
public class UtilsSecurity {

	private static byte[] securityKeyAuth = UUID.randomUUID().toString().concat(UUID.randomUUID().toString())
			.getBytes();
	private static LocalDateTime updateSecurityKey = LocalDateTime.now();

	/**
	 * Method to get user id from security context
	 * 
	 * @return
	 */
	public static Object getUserId() {
		Object id = null;
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		if (authentication != null) {
			RFUserDetails userDetails = (RFUserDetails) authentication.getPrincipal();
			if (userDetails != null) {
				id = userDetails.getUserId();
			}
		}
		return id;
	}

	/**
	 * Method to get security key
	 * 
	 * @return
	 */
	public static byte[] getSecurityKey() {
		LocalDateTime now = LocalDateTime.now();

		// Change key one time at least once a day
		if (securityKeyAuth == null || now.getDayOfYear() != updateSecurityKey.getDayOfYear() || now.getYear() != updateSecurityKey.getYear()
				|| now.getMonthValue() != updateSecurityKey.getMonthValue()) {
			synchronized (updateSecurityKey) {
				fixSecurityKey();
			}
		}

		return securityKeyAuth;
	}

	/**
	 * Method to generate bcrypt password
	 * 
	 * @param password
	 * @return
	 */
	public static String generateBcryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	/**
	 * Method to fix security key
	 */
	private synchronized static void fixSecurityKey() {
		LocalDateTime now = LocalDateTime.now();
		if (now.getDayOfYear() != updateSecurityKey.getDayOfYear() || now.getYear() != updateSecurityKey.getYear()
				|| now.getMonthValue() != updateSecurityKey.getMonthValue()) {
			securityKeyAuth = UUID.randomUUID().toString().concat(UUID.randomUUID().toString()).getBytes();
			updateSecurityKey = now;
		}
	}
}
