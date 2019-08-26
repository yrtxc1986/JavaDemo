package idv.wilson.demo.JPA_CreateBy;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaCreateByApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.isAuthenticated()).thenReturn(true);
		when(securityContext.getAuthentication().getPrincipal()).thenReturn("HeadCode");
		
		
		User newUser = new User();
		newUser.setName("Wilson");
		newUser.setEmail("keso.haneyou@gamil.com");
		User savedUser = userRepository.save(newUser);

		System.out.println(savedUser.getCreatedBy());
		System.out.println(savedUser.getCreatedDate());
		System.out.println(savedUser.getLastModifiedBy());
		System.out.println(savedUser.getLastModifiedDate());

		User readUser = userRepository.findByName("Wilson");

		System.out.println(readUser.getCreatedBy());
		System.out.println(readUser.getCreatedDate());
		System.out.println(readUser.getLastModifiedBy());
		System.out.println(readUser.getLastModifiedDate());
	}

}
