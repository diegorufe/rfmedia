package com.RFSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RFRest.beans.RequestResponse;
import com.RFRest.constants.IConstantsRest;
import com.RFSecurity.beans.AuthToken;
import com.RFSecurity.beans.LoginUser;
import com.RFSecurity.config.TokenProvider;
import com.RFSecurity.constants.IConstantsSecurity;
import com.RFSecurity.service.impl.UserServiceImpl;

/**
 * 
 * @author diego
 *
 */
@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IConstantsSecurity.REST_URL_AUTHENTICATION)
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@SuppressWarnings("unused")
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = IConstantsSecurity.REST_URL_AUTHENTICATION_GENERATE_TOKEN, method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new RequestResponse(new AuthToken(token), null));
	}
}
