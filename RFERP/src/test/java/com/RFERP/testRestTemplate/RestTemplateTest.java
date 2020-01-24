package com.RFERP.testRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.RFRest.beans.RequestResponse;

/**
 * 
 * @author diego
 *
 */
public class RestTemplateTest {

	public static void main(String[] args) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://localhost:8080/auth/token");
		LoginUserPost loginUserPost = new LoginUserPost();
		loginUserPost.setUsername("admin");
		loginUserPost.setPassword("1234");
		HttpEntity<LoginUserPost> request = new HttpEntity<>(loginUserPost);

		ResponseEntity<RequestResponse<PrincipalPost>> result = restTemplate.exchange(uri, HttpMethod.POST, request,
				new ParameterizedTypeReference<RequestResponse<PrincipalPost>>() {
				});
		System.out.println(result.getBody().getData());
	}
}
