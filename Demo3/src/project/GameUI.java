
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

//GUI class that handles the displaying of player info (lives/score) on the GUI
public class GameUI {
	
	//Instance variables
	private Avatar user;
	private String cHP = "Health: " + "";
	private String cSC = "Score: " + "";
	private Label currentHP = new Label(cHP);
	private Label currentScore = new Label(cSC);
	
	/* Constructor
	 * a - Avatar whose information is displayed on the GUI
	 */
	public GameUI(Avatar a) {
		user = a;
		currentHP.setMinWidth(100);
		currentHP.setMinHeight(100);
		currentScore.setMinWidth(100);
		currentScore.setMinHeight(100);
	}
	
	/* Method that creates and adds labels to a Pane
	 * root - The pane which holds the labels provided by GameUI
	 */
	public void establishLabels(Pane root) {
		VBox Org = new VBox();
		currentHP = new Label(cHP);
		currentScore = new Label(cSC);
		Org.getChildren().add(currentHP);
		Org.getChildren().add(currentScore);
		root.getChildren().add(Org);
	}
	
	// Method that updates labels any time there is a change in the player's info
	public void updateLabels() {
		this.cHP = "Current Health: " + user.getHealth();
		this.cSC = "Score: " + user.getScore();
		currentHP.setText(cHP);
		currentScore.setText(cSC);
		//currentHP.textProperty().bind(cHP);
		
	}
	
}
