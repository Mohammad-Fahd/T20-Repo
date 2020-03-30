package project;

import javafx.scene.Scene;

public class Obstacle extends Box {
	private String name;
	//Data type of speed is set in int. You can only move in the board composed by arry which indexes are all integer numbers
	// The data type of speed will fixed before moving in the diagram 
	private double speed; 
	private int xCood, yCood;
	private String direction;
	private int size = 1;
	
	//Regular Constructor to create a new obstacle item
	public Obstacle (int x, int y, double Speed){
		super(x,y,50);
		this.speed = Speed;
				
	}
	
	public Obstacle (int x, int y, String direction, int size)
	{
		super(x,y,size);
		this.speed = 0.85;
		this.direction = direction;
		
	}
	
	public Obstacle (int x, int y) {
		super(x,y,50);
	}
	
	//Obstacle moving
	public void moveR() {
		int newX = this.xCood;
		int newY = this.yCood;
		double newS = this.speed;
		newX += 1;
		if (newX >= 10) {
			newX = 0;
		}
		xCood = newX;
	}
	

	//Obstacle moving
	public void moveL() {
		int newX = this.xCood;
		int newY = this.yCood;
		double newS = this.speed;
		newX -= 1;
		if (newX <= -1 ) {
			newX = 9;
		}
		xCood = newX;
	}
	
    public void move(String direction) {
        if (direction.equals("Right") || direction.equals("R")) {
            super.moveR();
        }
        if (direction.equals("Left") || direction.equals("L")) {
            super.moveL();
        }
       
    }
	//overlapsWith method to check if there is a intersect between Avatar and Obstacle
	public boolean overlapsWith(Avatar a) {
		if (a.getX() == xCood && a.getY() == yCood)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public void setDirection(String direction)
	{
		this.direction = direction;
	}
	
	public void accelerate(int multipletimes) {
		int newX = this.xCood;
		int newY = this.yCood;
		double newS = this.speed;
		newX += newS*multipletimes;
		if (newX <= -1 || newX >= 15) {
			newX -= 1;
		}
	}
}
