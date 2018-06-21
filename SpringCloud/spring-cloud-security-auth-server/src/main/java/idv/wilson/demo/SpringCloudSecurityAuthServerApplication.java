package idv.wilson.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootApplication
@EnableResourceServer
public class SpringCloudSecurityAuthServerApplication {

	@Configuration
	@EnableAuthorizationServer
	protected class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

		@Autowired
		AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);

		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("browser").authorizedGrantTypes("refresh_token", "password").scopes("ui")
					.and().withClient("service-hi").secret("123456")
					.authorizedGrantTypes("client_credentials", "refresh_token", "password").scopes("server");
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityAuthServerApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence arg0) {
				return arg0.toString();
			}

			@Override
			public boolean matches(CharSequence arg0, String arg1) {
				return arg1.equals(arg0);
			}
		};
	}
}
