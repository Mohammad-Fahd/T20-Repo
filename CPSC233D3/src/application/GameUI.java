package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameUI {
	private Avatar user;
	private String cHP = "Health: " + "";
	private String cSC = "Score: " + "";
	private Label currentHP = new Label(cHP);
	private Label currentScore = new Label(cSC);
	
	public GameUI(Avatar a) {
		user = a;
		currentHP.setMinWidth(100);
		currentHP.setMinHeight(100);
		currentScore.setMinWidth(100);
		currentScore.setMinHeight(100);
	}
	
	public void establishLabels(Pane root) {
		VBox Org = new VBox();
		currentHP = new Label(cHP);
		currentScore = new Label(cSC);
		Org.getChildren().add(currentHP);
		Org.getChildren().add(currentScore);
		root.getChildren().add(Org);
	}
	
	public void updateLabels() {
		this.cHP = "Current Health: " + user.getHealth();
		this.cSC = "Score: " + user.getScore();
		currentHP.setText(cHP);
		currentScore.setText(cSC);
		//currentHP.textProperty().bind(cHP);
		
	}
	
}
