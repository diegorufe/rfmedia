package com.RFERP.testRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.RFRest.beans.RequestResponse;
import com.RFSecurity.beans.Performance;

/**
 * 
 * @author diego
 *
 */
public class RestTemplatePerformanceTest {

	public static void main(String[] args) throws URISyntaxException {
		long start = System.currentTimeMillis();
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://localhost:8080/auth/performance");

		ResponseEntity<RequestResponse<List<Performance>>> result = restTemplate.exchange(uri, HttpMethod.POST, null,
				new ParameterizedTypeReference<RequestResponse<List<Performance>>>() {
				});
		System.out.println("End "+(System.currentTimeMillis() - start));
		System.out.println(result.getBody().getData());
	}
}
