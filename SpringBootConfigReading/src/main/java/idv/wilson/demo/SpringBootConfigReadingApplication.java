package idv.wilson.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.var;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootConfigReadingApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringBootConfigReadingApplication.class, args);

		DBConfig dbConfig = ctx.getBean(DBConfig.class);
		ParaDMConfig appConfig = ctx.getBean(ParaDMConfig.class);

		System.out.println(dbConfig.getUrl());
		System.out.println(appConfig.getAppbaseurl());
	}

}
