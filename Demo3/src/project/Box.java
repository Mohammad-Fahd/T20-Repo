package project;

import javafx.scene.Scene;

// Parent class for logic classes. Contains methods that are common amongst all of them.
abstract class Box
{
	
	//Instance variables
	//How far the objects will move when given a movement command
	private double moveLen = 0.85;
	
	//The score given when collected by avatar
	private int scoreBoost;
	
	//x and y coordinates that are manipulated to move objects
	private double xCoord;
	private double yCoord;
	
	//instance variable that determines how much horizontal space is taken by the object
	private double width;
	
	/* Constructor for Box class
	 * x - Determines the beginning x coordinate of the box
	 * y - determines the beginning y coordinate of the box
	 * width - determines the width of the box (Horizontal space occupied)
	 */
	public Box(double x, double y, int width)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.width = width;
	}
	
	/* Copy Constructor
	 * toCopy - Box class that shares it's values
	 */
	public Box(Box toCopy)
	{
		this.xCoord = toCopy.getX();
		this.yCoord = toCopy.getY();
		this.width = toCopy.getWidth();
	}
	
	
	//Setters/Getters 
	public double getX()
	{
		return xCoord;
	}
	
	
	public double getY()
	{
		return yCoord;
	}
	

	public void setX(double newX)
	{
		this.xCoord = newX;
	}
	

	public void setY(double newY)
	{
		this.yCoord = newY;
	}
	
	
	public double getMoveLen()
	{
		return moveLen;
	}
	
	public void setMoveLen(double ml)
	{
		moveLen = ml;
	}
	
	//Used for testing purposes to print position of Box
	public void getCoord()
	{
		System.out.println("("+getX()+","+getY()+")");
	}
	
	public int getValue()
	{
		return scoreBoost;
	}
	
	public void updateValue(int value)
	{
		scoreBoost = value;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	//Moves the box right based on it's move length (instance variable initialized to .85)
	public void moveR()
	{
		setX(getX()+moveLen);
	}
	
	//Moves the object left
	public void moveL()
	{
		setX(getX()-moveLen);
	}
	
	//Checks to see if top-left or top-right corner of avatar
	//is within the Box
	//a - Avatar whose coordinates are being tested against Box
	public boolean withinBounds(Avatar a)
	{
		boolean flag = false;
		if((a.getX() < this.getWidth()+this.getX() && a.getWidth()+a.getX() >= this.getWidth()+this.getX())
		|| (a.getX() < this.getX() && a.getWidth()+a.getX() > this.getX()) 
		|| (a.getX() > this.getX() && a.getWidth()+a.getX() < this.getWidth()+this.getX()))
		{
			if(a.getY() == getY())
			{
				flag = true;
			}
		}

		return flag;
	}
	
	/* Abstract method to be used by child classes to determine their effects
	 * a - Avatar whose health/score will be manipulated
	 */
	void Interaction(Avatar a)
	{
		
	}

}
