package idv.wilson.demo.devonly;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@Configuration
@Order(99)
public class H2Console extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().sameOrigin();
		}
	
	    @Override
	     public void configure(WebSecurity web) throws Exception {
	         web.ignoring().antMatchers("/console/**");
	     }

	@Bean
	ServletRegistrationBean<WebServlet> h2servletRegistration() {
		ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(
				new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
}
