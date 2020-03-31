package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainScreen extends Pane {
	final int boardX = 550;
	final int boardY = 750;
	private Button bStart = new Button("START");
	private Label Title = new Label("FROGGER");
	
	public MainScreen() {
		setPrefSize(boardX, boardY);
		bStart.layoutXProperty().bind(widthProperty().subtract(bStart.widthProperty()).divide(2));
		bStart.layoutYProperty().bind(heightProperty().subtract(bStart.heightProperty()).divide(2));
		getChildren().add(bStart);
	}
	
	public void Interaction(Stage cStage, Scene Game) {
		bStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				cStage.setScene(Game);
			}
		});
	}
}
