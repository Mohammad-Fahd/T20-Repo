
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

//This class handles the logic surrounding the player character, stats and movement.

public class Avatar extends Box
{
	//instance variables
	private String name = "DEF";
	
	//Player originally starts with 3 lives/attempts and a score of 0
	//Score can be increased by getting to a goal or by collecting pickups
	private int health = 3;
	private int score = 0;
	
	//Coordinates that are manipulated based on where the user wants to take the avatar
	private double xCoord;
	private double yCoord;
	
	//Coordinates that are set and used to decide where the avatar will spawn at start and respawn
	private double originX;
	private double originY;
	
	//How many pixels the avatar will move with one move command.
	private final int moveLen = 50;
	
	/* Constructor
	 * xCoord - determines starting x Coordinate of avatar
	 * yCoord - determines starting y Coordinate of avatar 
	 */
	
	public Avatar(double xCoord, double yCoord) 
	{
		//Calls parent constructor to initialize the x and y coordinates
		super(xCoord,yCoord,1);
		
		//Sets origin to the initial coordinates given
		originX = xCoord;
		originY = yCoord;
		
		//Initializes the player's score to 0
		this.score = 0;
	}
	
	/* Constructor that takes an initial score as well
	 * xCoord - Determines starting x Coordinate of avatar
	 * yCoord - Determines starting y Coordinate of avatar
	 * cScore - Determines the score that the avatar will start with
	 */
	public Avatar(double xCoord, double yCoord, int cScore) {
		super(xCoord,yCoord,50);
		this.score = cScore;
	}
	
	/* Method that handles input through console
	 * direction - User input that determines which direction to move avatar in.
	 * correct input includes the characters 'U','D','L','R'
	 */
	//REQUIREMENT : char direction must be capitalized.
	//EDIT: Returns a new object with new coordinates to avoid privacy leak
	public Avatar move(char direction) 
	{
		//Creates variables to manipulate coordinates and score to prevent privacy leak
		double newX = this.xCoord;
		double newY = this.yCoord;
		int cScore = this.score;
		int movement = direction;
		switch(movement) {
		//Direction = 'U'
		case 85:
			//x,y
			newY -= 1;
			//If out of bounds, reverse the change.
			if (newY <= -1 || newY >= 15) {
				newY += 1;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'D'
		case 68:
			//x,y
			newY += 1;
			if (newY <= -1 || newY >= 15) {
					newY -= 1;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'L'
		case 76:
			newX -= 1;
			if (newX <= -1) {
					newX = 9;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'R'
		case 82:
			newX += 1;
			if (newX >= 10) {
					newX = 0;
			}
			return(new Avatar(newX, newY, cScore));
		//In the case that the above cases are not met, does not move the character
		default:
			return(new Avatar(newX, newY, cScore));
		}
	}
	
	/* Function that calls the EventHandler to check for key presses
	 * cScene - The scene that the avatar is displayed on (movements are translated onto it)
	 */
	 public void setMovement(Scene cScene) {
	        cScene.setOnKeyPressed(keyPressed);
	    }
	// Event handler that setMovement calls
	 private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch(event.getCode()) 
	            {
	            /* Looks at key press to determine if it used arrow keys, and moves
	             * the avatar in the direction that is indicated by the arrow key 
	             */
	                case UP:
	                    setY(getY() - moveLen);
	                    break;
	                case RIGHT:
	                    setX(getX() + moveLen);
	                    break;
	                case DOWN:
	                    setY(getY() + moveLen);
	                    break;
	                case LEFT:
	                    setX(getX() - moveLen);
	                    break;
	                default:
	                    System.out.println("NONE");
	                    break;
	            }
	        }
	    };
	   
	
	//getLocation() --> X and Y
	//Update Score: 'change' will have to be positive
	//when moving forwards, and negative when moving back.
	public void updateScore(int change) {
		score += change;
	}
	
	//Getter for score instance variable
	public int getScore() {
		return(score);
	}
	//Update Health: Setter method.
	public void updateHealth(int change) {
		health = change;
	}
	
	//Getter for health instance variable
	public int getHealth() {
		return(health);
	}
	
	//Resets position of the frog
	public void resetPos() {
		int cScore = this.score;
		setX(originX);
		setY(originY);
	}

}
