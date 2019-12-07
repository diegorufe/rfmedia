package com.RFSecurity.test.bcrypt;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.RFSecurity.utils.UtilsSecurity;

public class TestBcryptPassword {

	@Test
	public void test() {
		String password = "1234";
		assertTrue(new BCryptPasswordEncoder().matches(password, UtilsSecurity.generateBcryptPassword(password)));
	}
}
