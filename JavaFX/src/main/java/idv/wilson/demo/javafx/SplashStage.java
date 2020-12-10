package idv.wilson.demo.javafx;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SplashStage extends Preloader {
	Stage primaryStage = null;
	Label loadingMessage = new Label("Loading...");
	ProgressBar bar = new ProgressBar();

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setWidth(500);
		primaryStage.setHeight(300);

		HBox box = new HBox();
		box.getChildren().addAll(loadingMessage, bar);

		primaryStage.setScene(new Scene(box));
		primaryStage.show();
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info) {
		switch (info.getType()) {
		case BEFORE_START:
			primaryStage.hide();
			break;
		default:
			break;
		}
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification info) {
		if (info instanceof ProgressNotification) {
			bar.setProgress(((ProgressNotification) info).getProgress());
			log.trace(bar.getProgress() + "");
		}
	}

	@Override
	public boolean handleErrorNotification(ErrorNotification info) {
		// TODO Auto-generated method stub
		return super.handleErrorNotification(info);
	}

}