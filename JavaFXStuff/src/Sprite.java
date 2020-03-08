
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//base box that all moveables/items will build off of
abstract class Sprite extends Rectangle
{
	private double moveLen = 0.85;
	private int scoreBoost;
	
	//Constructor
	public Sprite(int x, int y, Color c)
	{
		setX(x);
		setY(y);
		setWidth(50);
		setHeight(50);
		setFill(c);
	}
	
	//Copy Constructor
	public Sprite(Sprite s)
	{
		setX(s.getX());
		setY(s.getY());
		setWidth(s.getArcWidth());
		setHeight(s.getArcHeight());
		setFill(s.getFill());
	}
	
	//Moves the object right 
	public void moveR(Scene s)
	{
		setX(getX()+moveLen);
	}
	
	//Moves the object left
	public void moveL(Scene s)
	{
		setX(getX()-moveLen);
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
	
	void Interaction(Avatar a, Scene cScene)
	{
		
	}

	

}