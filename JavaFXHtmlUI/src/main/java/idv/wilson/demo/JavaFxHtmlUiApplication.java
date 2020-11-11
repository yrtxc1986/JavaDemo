package idv.wilson.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class JavaFxHtmlUiApplication {

	public static void main(String[] args) {
		// SpringApplication.run(JavaFxHtmlUiApplication.class, args);
		Application.launch(JavaFxApplication.class, args);
	}

}
