

import javafx.scene.Scene;

//Parent class for most objects that are to collide with the avatar
public class Obstacle extends Box 
{
	private String name;
	//Data type of speed is set in int. You can only move in the board composed by arry which indexes are all integer numbers
	// The data type of speed will fixed before moving in the diagram 
	private double speed; 
	private int xCood, yCood;
	private String direction;
	
	//Determines how much space is taken up by the obstacle (x50 pixels)
	private int size = 1;
	
	//Regular Constructor to create a new obstacle item
	public Obstacle (int x, int y, double Speed){
		super(x,y,50);
		this.speed = Speed;
				
	}
	
	//Constructor that is also used to adjust the size of the created object
	public Obstacle (int x, int y, String direction, int size)
	{
		super(x,y,size);
		this.speed = 0.85;
		this.direction = direction;
		
	}
	
	//Constructor that only takes in the starting x and y coordinates of the obstacle
	public Obstacle (int x, int y) {
		super(x,y,50);
	}
	
	//Obstacle moving Right
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
	

	//Obstacle moving Left
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
	
	/* Method that checks direction variable to determine whether the obstacle
	 * moves left or right, and then moves it accordingly
	 * direction - String that is used to indicate movement direction
	 * 
	 */
    public void move(String direction) {
        if (direction.equals("Right") || direction.equals("R")) {
            super.moveR();
        }
        if (direction.equals("Left") || direction.equals("L")) {
            super.moveL();
        }
       
    }
	//overlapsWith method to check if there is a intersect between Avatar and Obstacle
    //Used for text-based version.
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
	
	//Getters/Setters
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
