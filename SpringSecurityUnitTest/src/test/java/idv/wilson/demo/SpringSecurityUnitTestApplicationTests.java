package idv.wilson.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class SpringSecurityUnitTestApplicationTests {

	@Autowired
	ServiceWithSecurityContext service;

	@Test
	@WithMockUser(username = "user", authorities = { "ADMIN", "USER" })
	public void Test() {

		assertThat(service.getCurrentUserName()).isEqualTo("user");
	}

}
