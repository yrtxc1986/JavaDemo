package idv.wilson.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ServiceWithSecurityContext {

	public String getCurrentUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
