package project;

import javafx.scene.paint.Color;
import java.util.ArrayList;
public class Collectible extends Box 
{
	private int name;
	private int scoreBoost, xCood, yCood;
	private int speed = 50;
	private Color color;
	
	//Regular Constructor to create a new collectible item
	public Collectible(int x, int y, int boost, int desig)
	{
		super(x,y,50);
		scoreBoost = boost;
		name = desig;
		if(name == 1)
		{
			color = Color.PURPLE;
		}
		else if(name == 4)
		{
			color = Color.GOLD;
		}
		setWidth(1);
	}
	
	//Copy constructor to inherit the properties of a pre-existing Collectible object
	public Collectible(Collectible c)
	{
		super(c);
		setWidth(1);
	}
	
	//Checks to see if Avatar is on top of collectible item
	public boolean overlapsWith(Avatar a)
	{
		if (a.getX() == xCood && a.getY() == yCood)
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
	
	//Setters for name,value and speed
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
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	
	//Getter methods
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
	
	public Color getColor()
	{
		return color;
	}
	
	public void resetCollectible(ArrayList<Box> b)
	{
		for(Box c: b)
		{
			if(c instanceof Collectible)
			{
				if(((Collectible)c).getName() == 0)
				{
					((Collectible)c).updateName(1);
					((Collectible)c).updateValue(300);
					((Collectible)c).setColor(Color.PURPLE);
				}
			}
		}
	}
	
	@Override
	public void Interaction(Avatar a)
	{
		if(withinBounds(a))
		{
			a.updateScore(getValue());
			updateValue(0);
			if(name == 4)
			{
				a.resetPos();
				setColor(Color.DARKGREEN);
			}
			else if(name == 1)
			{
				color = Color.TRANSPARENT;
				updateName(0);
			}
		}
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
}