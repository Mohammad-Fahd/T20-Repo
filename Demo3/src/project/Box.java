package project;

import javafx.scene.Scene;

abstract class Box
{
	private double moveLen = 0.85;
	private int scoreBoost;
	private double xCoord;
	private double yCoord;
	private double width;
	
	public Box(double x, double y, int width)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.width = width;
	}
	
	public Box(Box toCopy)
	{
		this.xCoord = toCopy.getX();
		this.yCoord = toCopy.getY();
		this.width = toCopy.getWidth();
	}
	
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
	
	//Setters/Getters (x/y already have them as per Rectangle class)
	public double getMoveLen()
	{
		return moveLen;
	}
	
	public void setMoveLen(double ml)
	{
		moveLen = ml;
	}
	
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
	//is within the sprite
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
	
	void Interaction(Avatar a)
	{
		
	}

}
