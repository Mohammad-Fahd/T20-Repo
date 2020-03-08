package project;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Collectible extends Sprite
{
	private int name;
	private int scoreBoost, xCood, yCood;
	private int speed = 50;
	
	//Regular Constructor to create a new collectible item
	public Collectible(int x, int y, int boost, int desig)
	{
		super(x,y,Color.PURPLE);
		scoreBoost = boost;
		name = desig;
		if(name == 4)
		{
			setFill(Color.GOLD);
		}
	}
	
	//Copy constructor to inherit the properties of a pre-existing Collectible object
	public Collectible(Collectible c)
	{
		super(c);
		this.name = c.getName();
		this.scoreBoost = c.getValue();
		
	}
	
	//Checks to see if Avatar is on top of collectible item
	public boolean overlapsWith(Avatar a)
	{
		if (a.getXCoord() == xCood && a.getYCoord() == yCood)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	//Changes location of the collectible item(Setter method for both x and y Coordinates)
	public void updateLocation(int x, int y)
	{
		xCood = x;
		yCood = y;
	}
	
	//Setters for name,value and speed(x/y already have them as per Rectangle class)
	public void updateName(int desig)
	{
		name = desig;
	}
	
	public void updateValue(int value)
	{
		scoreBoost = value;
	}
	
	public void updateSpeed(int s)
	{
		speed = s;
	}
	
	
	//Getter methods(getX and getY are part of the original rectangle class)
	
	public int getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return scoreBoost;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	//Allows collectible item to move back and forth
	//Change to char using the 'U','L','D','R' system if required
	//Add check to see if new location overlaps with an obstacle if required
	public void move(String direction)
	{
		if(direction.equalsIgnoreCase("left"))
		{
			xCood -= speed;
		}
		else if(direction.equalsIgnoreCase("right"))
		{
			xCood += speed;
		}
		else if(direction.equalsIgnoreCase("up"))
		{
			yCood += speed;
		}
		else if(direction.equalsIgnoreCase("down"))
		{
			yCood -= speed;
		}
		
	}
	
	void Interaction(Avatar a, Scene cScene)
	{
		
	}
}