package com.RFERP.controller.security.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RFCore.utils.string.UtilsString;
import com.RFCoreSecurity.constants.EnumParamOptionsSecurity;
import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFCoreSecurity.service.IPostAutenticationService;
import com.RFRest.beans.RequestResponse;
import com.RFRest.constants.IConstantsRest;
import com.RFSecurity.beans.AuthToken;
import com.RFSecurity.beans.LoginUser;
import com.RFSecurity.beans.Performance;
import com.RFSecurity.beans.Principal;
import com.RFSecurity.beans.RFUserDetails;
import com.RFSecurity.config.TokenProvider;
import com.RFSecurity.service.impl.UserSecurityServiceImpl;

/**
 * 
 * @author diego
 *
 */
@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IConstantsSecurity.REST_URL_AUTHENTICATION)
public class ERPAuthenticationController {

	private static final Logger LOG = LoggerFactory.getLogger(ERPAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@SuppressWarnings("unused")
	@Autowired
	private UserSecurityServiceImpl userSecurityService;

	@Autowired(required = false)
	private IPostAutenticationService postAutenticationService;

	@RequestMapping(value = IConstantsSecurity.REST_URL_AUTHENTICATION_GENERATE_TOKEN, method = RequestMethod.POST)
	public ResponseEntity<?> token(@RequestBody LoginUser loginUser, HttpServletResponse res)
			throws AuthenticationException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("User is triying auntetication...");
		}
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		RFUserDetails rfUserDetails = (RFUserDetails) authentication.getPrincipal();

		Map<String, Object> params = new HashMap<String, Object>();

		// Load extra data post autentication
		if (this.postAutenticationService != null) {
			params.put(EnumParamOptionsSecurity.PARAM_SECURITY_USER_ID.getValue(), rfUserDetails.getUserId());
			params = this.postAutenticationService.postAutenticate(params);
		}

		String token = jwtTokenUtil.generateToken(authentication);
		// Se token in header for refresh in header
		res.addHeader(IConstantsSecurity.HEADER_STRING, IConstantsSecurity.TOKEN_PREFIX.concat(token));

		Principal principal = new Principal();
		principal.setNick(rfUserDetails.getUsername());
		principal.setId(rfUserDetails.getUserId());
		principal.setToken(token);

		return ResponseEntity.ok(new RequestResponse<Principal>(principal, null));
	}

	@RequestMapping(value = IConstantsSecurity.REST_URL_AUTHENTICATION_REFRESH_TOKEN, method = RequestMethod.POST)
	public ResponseEntity<?> refresh(@RequestBody AuthToken token, HttpServletResponse res)
			throws AuthenticationException {
		String refreshToken = jwtTokenUtil.refreshToken(token);
		if (UtilsString.isBlank(refreshToken)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok(new RequestResponse<String>(refreshToken, null));
		}
	}

	@RequestMapping(value = IConstantsSecurity.REST_URL_AUTHENTICATION_TEST_PERFORMANCE, method = RequestMethod.POST)
	public ResponseEntity<?> performance() {
		List<Performance> listPerformance = new ArrayList<Performance>();
		Performance performance = null;
		for (int i = 0; i < 1000; i++) {
			performance = new Performance();
			performance.setId(15);
			performance.setCode("Code " + i);
			listPerformance.add(performance);
		}
		return ResponseEntity.ok(new RequestResponse<List<Performance>>(listPerformance, null));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
