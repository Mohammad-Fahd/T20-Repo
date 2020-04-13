package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Class that handles the mainscreen GUI, which allows the user to start the game
public class MainScreen extends Pane
{
	//Instance variables
	//Determines the size of the main menu
	final int boardX = 550;
	final int boardY = 750;
	
	//variable to create buttons and title of the main menu
	private Button bStart = new Button("START");
	private Label Title = new Label("FROGGER");
	
	//Constructor
	public MainScreen() 
	{
		//Sets size of main menu
		setPrefSize(boardX, boardY);
		bStart.layoutXProperty().bind(widthProperty().subtract(bStart.widthProperty()).divide(2));
		bStart.layoutYProperty().bind(heightProperty().subtract(bStart.heightProperty()).divide(2));
		getChildren().add(bStart);
	}
	
	/* Method to start game once start button is pressed
	 * cStage - The stage that main menu is in
	 * Game - The scene that contains the main game
	 */
	public void Interaction(Stage cStage, Scene Game) {
		bStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				cStage.setScene(Game);
			}
		});
		
	}
	
	/* Method that checks to see if Avatar has lost all lives, then resets and goes back to 
	 * main menu
	 * cStage -  The stage that contains the main game and the main menu
	 * mainScreen - The scene that contains the mainScreen to switch back to
	 * a - Avatar whose lives are checked(and stats reset if out of lives)
	 */
	public void resetGame(Stage cStage,Scene mainScreen ,Avatar a)
	{
		if(a.getHealth() == 0)
		{
			cStage.setScene(mainScreen);
			a.updateHealth(3);
			a.updateScore(a.getScore()*-1);
		}
	}
}