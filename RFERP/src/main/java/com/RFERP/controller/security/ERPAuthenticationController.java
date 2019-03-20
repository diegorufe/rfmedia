package com.RFERP.controller.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFRest.constants.IConstantsRest;
import com.RFSecurity.constants.IConstantsSecurity;
import com.RFSecurity.controller.AuthenticationController;

@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IConstantsSecurity.REST_URL_AUTHENTICATION)
public class ERPAuthenticationController extends AuthenticationController {
}
