package idv.wilson.demo.javafx;

import org.springframework.stereotype.Component;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@Component
public class TitleBar extends HBox {

	private double x, y;

	public TitleBar() {
		this.setHeight(30);
		this.setMinHeight(30);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		this.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event) -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});
	}

}
