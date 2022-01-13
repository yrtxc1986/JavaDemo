package idv.wilson.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="paradm")
public class ParaDMConfig {

	private String appbaseurl;
}
