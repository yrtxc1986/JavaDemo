package idv.wilson.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "sql")
public class SQLConfig {
	String driver;
	String url;
	String username;
	String password;
	boolean defaultAutoCommit;
	boolean defaultReadOnly;
	int maxActive;
	int maxIdle;
	int maxWait;
	boolean poolPreparedStatements;
	int maxOpenPreparedStatements;
	int defaultTransactionIsolation;
	boolean logAbandoned;
	boolean removeAbandoned;
	int removeAbandonedTimeout;
	boolean testWhileIdle;
}
