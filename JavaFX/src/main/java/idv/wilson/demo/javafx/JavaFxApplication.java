package idv.wilson.demo.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class JavaFxApplication {

	public static void main(String[] args) {
		System.setProperty("javafx.preloader", SplashStage.class.getCanonicalName());
		Application.launch(MainStage.class, args);
	}

}
