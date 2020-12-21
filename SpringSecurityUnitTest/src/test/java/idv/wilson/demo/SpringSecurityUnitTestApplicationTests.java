package idv.wilson.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import idv.wilson.demo.unitHelper.WithMockCustomUser;

@SpringBootTest
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityUnitTestApplicationTests {

	@Autowired
	ServiceWithSecurityContext service;

	@Test
	@WithMockCustomUser(userid = "3")
	public void Test() {
		assertThat(service.getCurrentUserName()).isEqualTo("user");
	}

}
