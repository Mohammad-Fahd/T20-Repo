package project;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Avatar extends Box
{
	private String name = "DEF";
	private int health = 3;
	private int score = 0;
	private double xCoord;
	private double yCoord;
	private double originX;
	private double originY;
	private final int moveLen = 50;
	
	public Avatar(double xCoord, double yCoord) {
		super(xCoord,yCoord,1);
		originX = xCoord;
		originY = yCoord;
		this.score = 0;
	}
	
	public Avatar(double xCoord, double yCoord, int cScore) {
		super(xCoord,yCoord,50);
		this.score = cScore;
	}
	
	//REQUIREMENT : char direction must be capitalized.
	//EDIT: Returns a new object with new coordinates to avoid privacy leak
	public Avatar move(char direction) {
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
		default:
			return(new Avatar(newX, newY, cScore));
		}
	}
	
	 public void setMovement(Scene cScene) {
	        cScene.setOnKeyPressed(keyPressed);
	    }
	
	 private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch(event.getCode()) {
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
	
	public int getScore() {
		return(score);
	}
	//Update Health: Setter method.
	public void updateHealth(int change) {
		health = change;
	}
	
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