package idv.wilson.demo;

import java.net.URL;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaFxApplication extends Application {

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() throws Exception {
		String[] args = getParameters().getRaw().toArray(new String[0]);

		this.applicationContext = new SpringApplicationBuilder().sources(JavaFxHtmlUiApplication.class).headless(false)
				.web(WebApplicationType.NONE).run(args);
		applicationContext.getBeanFactory().registerSingleton("HostServices", this.getHostServices());
	}

	@Override
	public void start(Stage stage) {

		stage.setTitle("CryptoBox Finder");

		var rootPane = new VBox();

		rootView(rootPane);

		stage.setScene(new Scene(rootPane, 640, 480));
		stage.show();

	}

	private void rootView(Pane pane) {

		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		URL url = this.getClass().getResource("/Ui/index.html");
		webEngine.load(url.toString());

		browser.getEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {

			if (newState == Worker.State.SUCCEEDED) {
				var document = webEngine.getDocument();
				var element = document.getElementById("alert");
				((EventTarget) element).addEventListener("click", new EventListener() {
					public void handleEvent(Event ev) {
						log.debug("Button is click");
					}
				}, false);

			}
		});

		pane.getChildren().add(browser);
	}

	@Override
	public void stop() {
		this.applicationContext.close();
		Platform.exit();
	}
}
