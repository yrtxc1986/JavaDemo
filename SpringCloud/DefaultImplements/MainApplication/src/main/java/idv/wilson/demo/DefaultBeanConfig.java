package idv.wilson.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import idv.wilson.demo.defaultImplements.service.HelloService;
import idv.wilson.demo.defaultImplements.service.HelloServiceImpl;

@Configuration
public class DefaultBeanConfig {

	@Bean
	@ConditionalOnMissingBean
	public HelloService helloService() {
		return new HelloServiceImpl();
	}
}
