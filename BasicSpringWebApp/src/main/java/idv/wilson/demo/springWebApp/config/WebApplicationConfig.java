package idv.wilson.demo.springWebApp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan("idv.wilson.demo.springWebApp")
public class WebApplicationConfig implements WebMvcConfigurer {

	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}

//	@Bean
//	public UrlBasedViewResolver viewResolver() {
//		UrlBasedViewResolver bean = new UrlBasedViewResolver();
//		bean.setViewClass(TilesView.class);
//		return bean;
//	}
//
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer bean = new TilesConfigurer();
//
//		return bean;
//	}
}
