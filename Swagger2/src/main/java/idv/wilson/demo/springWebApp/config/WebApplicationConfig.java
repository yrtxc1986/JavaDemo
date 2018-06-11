package idv.wilson.demo.springWebApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("idv.wilson.demo.springWebApp")
public class WebApplicationConfig implements WebMvcConfigurer {
	//
	// @Bean
	// @Scope("prototype")
	// public Logger logger(InjectionPoint ip) {
	// return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	// }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}