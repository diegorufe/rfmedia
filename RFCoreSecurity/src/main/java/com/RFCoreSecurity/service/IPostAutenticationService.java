package com.RFCoreSecurity.service;

import java.util.Map;

/**
 * 
 * @author diego
 *
 */
public interface IPostAutenticationService {
	
	public Map<String, Object> postAutenticate(Map<String, Object> params);
	
}