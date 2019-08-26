package idv.wilson.demo.JPA_CreateBy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaCreateByApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaCreateByApplication.class, args);
	}

}
