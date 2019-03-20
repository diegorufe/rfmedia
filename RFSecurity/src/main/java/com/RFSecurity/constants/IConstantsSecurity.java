package com.RFSecurity.constants;

/**
 * 
 * @author diego
 *
 */
public interface IConstantsSecurity {
	public static final String SC_UNAUTHORIZED_MESSAGE = "Unauthorized";
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
	public static final String SIGNING_KEY = "rfmedia";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final String URL_TOKEN = "/token/*";
	public static final String URL_SIGNUP = "/login";
	public static final String SERVICE_USER = "userService";
	public static final String REST_URL_AUTHENTICATION = "/token";
	public static final String REST_URL_AUTHENTICATION_GENERATE_TOKEN = "/generate-token";
}
