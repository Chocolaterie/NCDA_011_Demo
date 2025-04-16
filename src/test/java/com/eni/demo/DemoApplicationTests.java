package com.eni.demo;

import com.eni.demo.auth.AuthService;
import com.eni.demo.auth.AuthUser;
import com.eni.demo.service.ServiceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	AuthService authService;

	@Test
	void contextLoads() {
		// test 1
		ServiceResponse<AuthUser> response01 = authService.login("toto@gmail.com", "password");
		Assert.isTrue(response01.code.equals("200"), response01.message);

		// test 2
		ServiceResponse<AuthUser> response02 = authService.login("toto@gmail.com", "password2");
		Assert.isTrue(response02.code.equals("746"), response02.message);

		// test 3
		ServiceResponse<AuthUser> response03 = authService.login("toto2@gmail.com", "password");
		Assert.isTrue(response03.code.equals("746"), response03.message);
	}

}
