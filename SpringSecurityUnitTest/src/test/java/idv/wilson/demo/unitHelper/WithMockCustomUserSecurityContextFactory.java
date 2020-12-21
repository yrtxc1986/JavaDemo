package idv.wilson.demo.unitHelper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import idv.wilson.demo.MyUser;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();

		MyUser principal = new MyUser(customUser.username(), "password", true, true, true, true,
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		principal.setUserid(customUser.userid());
		principal.setEmail(customUser.email());

		Authentication auth = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
		context.setAuthentication(auth);
		return context;
	}
}