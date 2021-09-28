package idv.wilson.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "jpa.adapter")
public class JPAConfig {
	boolean generateDdl;
	boolean showSql;
	String database;
	String databasePlatform;
}
