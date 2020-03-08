  
package project;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Sprite {
	private String name;
	//Data type of speed is set in int. You can only move in the board composed by arry which indexes are all integer numbers
	// The data type of speed will fixed before moving in the diagram 
	private double speed; 
	private int xCood, yCood;
	
	//Regular Constructor to create a new obstacle item
	public Obstacle (int x, int y, double Speed){
		super(x,y,Color.RED);
		this.speed = Speed;
				
	}
	
	public Obstacle (int x, int y) {
		super(x,y);
		this.speed = getSpeed();
	}
	
	public Obstacle (Obstacle o) {
		super(o);
		this.speed = o.getSpeed();
	}
	//Setters an Getters
	public int getX() {
		return(xCood);
	}
	
	public int getY() {
		return(yCood);
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public void updateSpeed(int Speed)
	{
		speed = Speed;
	}
	
	//Obstacle moving
		public Obstacle moveR() {
			int newX = this.xCood;
			int newY = this.yCood;
			double newS = this.speed;
			newX += newS;
			if (newX >= 10) {
				newX -= 1;
			}
			return(new Obstacle(newX, newY));
		}
		
	//Obstacle moving
	public Obstacle moveL() {
		int newX = this.xCood;
		int newY = this.yCood;
		int newS = this.speed;
		newX -= newS;
		if (newX <= -1 ) {
			newX += 1;
		}
		return(new Obstacle(newX, newY));
	}
	
	//overlapsWith method to check if there is a intersect between Avatar and Obstacle
	public boolean overlapsWith(Avatar a) {
		if (a.getXCoord() == xCood && a.getYCoord() == yCood)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//public boolean isDeadly(Avatar a) {
		//if (a.getyCood()>=1 && a.getyCood()<=8 && overlapsWith(a) == true) {
			//return true;
		//}
		//else if (a.getyCood()>=9 && a.getyCood()<=14 && overlapsWith(a) == false) {
			//return true;
		//}
		//else {
			//return false;
		//}
	//}
	//The data type of multipletimes is int, the seem reason as the data type of speed should be int 
	public void accelerate(int multipletimes) {
		int newX = this.xCood;
		int newY = this.yCood;
		int newS = this.speed;
		newX += newS*multipletimes;
		if (newX <= -1 || newX >= 15) {
			newX -= 1;
		}
	}
}

