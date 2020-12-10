package idv.wilson.demo.javafx;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import idv.wilson.demo.javafx.ui.MainUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);

		VBox box = new VBox();

		box.getChildren().addAll(applicationContext.getBean(TitleBar.class), applicationContext.getBean(MainUI.class));

		primaryStage.setScene(new Scene(box));
		primaryStage.show();

	}

	private ConfigurableApplicationContext applicationContext;

	private int TOTAL_JOB = 5;

	@Override
	public void init() throws Exception {
		String[] args = getParameters().getRaw().toArray(new String[0]);
		applicationContext = new SpringApplicationBuilder().sources(JavaFxApplication.class).run(args);
		notifyPreloader(new Preloader.ProgressNotification(3.0 / TOTAL_JOB));

		for (double i = 3; i < 5; i++) {
			// other long run init.
			TimeUnit.SECONDS.sleep(1);
			notifyPreloader(new Preloader.ProgressNotification(i / TOTAL_JOB));
		}
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
		Platform.exit();
	}

}
