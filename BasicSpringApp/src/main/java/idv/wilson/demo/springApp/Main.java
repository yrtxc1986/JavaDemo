package idv.wilson.demo.springApp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import idv.wilson.demo.springApp.config.ApplicationConfig;

@Component
public class Main {

	@Autowired
	private Logger logger;

	public void run() {
		logger.info("Logger Hello");
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		Main m = context.getBean(Main.class);
		m.run();
	}
}
