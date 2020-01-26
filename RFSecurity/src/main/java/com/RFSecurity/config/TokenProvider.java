package com.RFSecurity.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFSecurity.beans.RFUserDetails;
import com.RFSecurity.utils.UtilsSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author diego
 *
 */
public class TokenProvider {
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(UtilsSecurity.getSecurityKey()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication authentication) {
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		RFUserDetails user = (RFUserDetails) authentication.getPrincipal();
		return Jwts.builder().setSubject(authentication.getName())
				.claim(IConstantsSecurity.AUTHORITIES_KEY, authorities)
				.claim(IConstantsSecurity.USER_ID, user.getUserId())
				.signWith(SignatureAlgorithm.HS256, UtilsSecurity.getSecurityKey())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(
						new Date(System.currentTimeMillis() + IConstantsSecurity.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.compact();
	}

	public Boolean validateToken(String token, String username) {
		final String usernameToken = getUsernameFromToken(token);
		return (usernameToken.equals(username) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final String username) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(UtilsSecurity.getSecurityKey());

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(IConstantsSecurity.AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(
				new RFUserDetails(username, "", authorities, (Integer) claims.get(IConstantsSecurity.USER_ID)), "",
				authorities);
	}
}
