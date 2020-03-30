package project;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//base box that all moveables/items will build off of
public class Sprite extends Rectangle
{
	private double moveLen = 0.85;
	private int scoreBoost;
	
	//Constructor
	public Sprite(Box b)
	{
		setX(b.getX());
		setY(b.getY());
		if(b instanceof Logs)
		{
			changeColor(Color.BROWN);
		}
		else if(b instanceof Wall)
		{
			changeColor(Color.DARKGRAY);
		}
		else if(b instanceof Avatar)
		{
			changeColor(Color.BLACK);
		}
		else if(b instanceof Collectible)
		{
			if(((Collectible)b).getName() == 4)
			{
				changeColor(Color.GOLD);
			}
			if(((Collectible)b).getName() == 1)
			{
				changeColor(Color.PURPLE);
			}
		}
		super.setWidth(50*b.getWidth());
		b.setWidth(this.getWidth());
		setHeight(50);
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
	
	   public void checkGame(Pane root,Avatar a) {
	    	if (a.getHealth() == 0) {
	    		Label GO = new Label("GAME OVER");
	    		GO.setMinWidth(100);
	    		GO.setMinHeight(100);
	    		GO.layoutXProperty().bind(root.widthProperty().subtract(GO.widthProperty()).divide(2));
	    		GO.layoutYProperty().bind(root.heightProperty().subtract(GO.heightProperty()).divide(2));
	    		root.getChildren().add(GO);
	    		//scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
	    	}
	    }
	
	
    //Changes colour
    public void changeColor(Color newColor) {
    	super.setFill(newColor);
    }
}