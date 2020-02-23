
public class Collectible 
{
	private int name;
	private int scoreBoost, xCood, yCood;
	private int speed;
	
	//Regular Constructor to create a new collectible item
	public Collectible(int x, int y, int boost, int desig)
	{
		xCood = x;
		yCood = y;
		scoreBoost = boost;
		name = desig;
	}
	
	//Copy constructor to inherit the properties of a pre-existing Collectible object
	public Collectible(Collectible c)
	{
		Collectible b = new Collectible(c);
		this.name = b.name;
		this.xCood = b.xCood;
	}
	
	//Checks to see if avatar is on top of collectible item
	public boolean overlapsWith(Avatar a)
	{
		if (a.getXCoord() == xCood && a.getYCoord() == yCood)
		{
			a.updateScore(scoreBoost);
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
	
	
	
	//Getter methods
	public int getX()
	{
		return xCood;
	}
	
	public int getY()
	{
		return yCood;
	}
	
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
}
