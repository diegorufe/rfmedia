package com.RFERP.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RFERP.constants.IUrlsERP;
import com.RFRest.beans.RequestResponse;
import com.RFRest.constants.IConstantsRest;

/**
 * 
 * @author diego
 *
 */
@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IUrlsERP.URL_TEST)
public class TestController {

	@RequestMapping(value = IUrlsERP.URL_TEST_HELLO, method = RequestMethod.POST)
	public ResponseEntity<?> hello() throws AuthenticationException {
		return ResponseEntity.ok(new RequestResponse("HEllO", null));
	}
}
