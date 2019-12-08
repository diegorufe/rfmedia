package com.RFSecurity.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFSecurity.config.TokenProvider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @author diego
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(IConstantsSecurity.HEADER_STRING);
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith(IConstantsSecurity.TOKEN_PREFIX)) {
			authToken = header.replace(IConstantsSecurity.TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			if (jwtTokenUtil.validateToken(authToken, username)) {

				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,
						SecurityContextHolder.getContext().getAuthentication(), username);
				// UsernamePasswordAuthenticationToken authentication = new
				// UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new
				// SimpleGrantedAuthority("ROLE_ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				logger.info("authenticated user " + username + ", setting security context");
				String token = jwtTokenUtil.generateToken(authentication);
				res.addHeader(IConstantsSecurity.HEADER_STRING, IConstantsSecurity.TOKEN_PREFIX.concat(token));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}
}