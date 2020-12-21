package idv.wilson.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithSecurityContext {

	@PreAuthorize("hasRole('USER')")
	public String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUser user = (MyUser) auth.getPrincipal();
		System.out.println(user.getUserid());
		System.out.println(user.getEmail());

		return auth.getName();
	}
}
