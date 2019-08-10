package com.RFSecurity.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.RFSecurity.beans.RFUserDetails;

/**
 * 
 * @author diego
 *
 */
public class UtilsSecurity {

	/**
	 * Method to get user id from security context
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		Integer id = null;
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
}
