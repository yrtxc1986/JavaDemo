package idv.wilson.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import idv.wilson.demo.security.CustAuthenticationProvider;

@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	CustAuthenticationProvider custAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		ProviderManager authenticationManager = new ProviderManager(
				Arrays.asList(custAuthenticationProvider));
		// 不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
		authenticationManager.setEraseCredentialsAfterAuthentication(false);
		return authenticationManager;
	}
}
