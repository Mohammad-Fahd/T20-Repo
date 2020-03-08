import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
 
public class Avatar extends Rectangle {
   
    private String name = "DEF";
    private int health = 3;
    private int score;
    private int xCoord = 350;
    private int yCoord = 700;
    private final int moveLen = 50;
    //Upper Left Corner = xCoord, yCoord
    //xCoord Start = 350px
    //yCoord Start = 700px
    public Avatar() {
        setX(xCoord);
        setY(yCoord);
        System.out.println(getX());
        System.out.println(getY());
        setFill(Color.BLACK);
        setWidth(50);
        setHeight(50);
        this.score = 0;
    }
   
    public Avatar(int xCoord, int yCoord, int cScore) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.score = cScore;
    }
   
    public Avatar(Avatar toCopy) {
        this.xCoord = toCopy.getXCoord();
        this.yCoord = toCopy.getYCoord();
        this.score = toCopy.getScore();
    }
   
    public void setMovement(Scene cScene) {
        cScene.setOnKeyPressed(keyPressed);
    }
   
    public void updatePos() {
        System.out.println(getX());
        System.out.println(getY());
    }
   
    private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch(event.getCode()) {
                case UP:
                    setY(getY() - moveLen);
                    break;
                case RIGHT:
                    setX(getX() + moveLen);
                    break;
                case DOWN:
                    setY(getY() + moveLen);
                    break;
                case LEFT:
                    setX(getX() - moveLen);
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    };
   
    //getLocation() --> X and Y Coordinates (centre)
    public int getXCoord() {
        return(xCoord);
    }
   
    public int getYCoord() {
        return(yCoord);
    }
   
    //Update Score: 'change' will have to be positive
    //when moving forwards, and negative when moving back.
    public void updateScore(int change) {
        score += change;
    }
   
    public int getScore() {
        return(score);
    }
    //Update Health: Setter method.
    public void updateHealth(int change) {
        health = change;
    }
   
    public int getHealth() {
        return(health);
    }
   
    public void checkGame(Pane root) {
    	if (this.getHealth() == 0) {
    		Label GO = new Label("GAME OVER");
    		GO.setMinWidth(100);
    		GO.setMinHeight(100);
    		GO.layoutXProperty().bind(root.widthProperty().subtract(GO.widthProperty()).divide(2));
    		GO.layoutYProperty().bind(root.heightProperty().subtract(GO.heightProperty()).divide(2));
    		root.getChildren().add(GO);
    		//scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
    	}
    }
    //Resets position of the frog
    public void resetPos() {
        final int originX = 350;
        final int originY = 700;
        int cScore = this.score;
        this.setX(originX);
        this.setY(originY);
        
        
    }
    
    public boolean overlapsWith(Sprite s)
	{
		if (s.getX() == getX() && s.getY() == getY())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    
    public void collision(ArrayList<Sprite> a,Scene cScene)
    {
    	for(Sprite s:a)
        {
        	if(s.withinBounds(this))
        	{
        		if(s instanceof Logs)
        		{
        			
        		}
        		else if(s instanceof Obstacle)
        		{
        			resetPos();
        			updateHealth(getHealth()-1);
        		}
        		if(s instanceof Collectible)
        		{
        			Collectible c = new Collectible((Collectible) s);
        			updateScore(getScore()+c.getValue());
        			s.setFill(Color.rgb(200, 200, 200, 0.0));
        			s.updateValue(0);
        			if(c.getName() == 4)
        			{
        				this.resetPos();
        				for(Sprite spr: a)
        				{
        					if(spr instanceof Collectible)
        					{
        						if(((Collectible)spr).getName() == 1)
        						{
        							spr.setFill(Color.PURPLE);
        							spr.updateValue(300);
        						}
        					}
        				}
        				((Collectible) s).updateName(2);
        				
        			}
        				
        		
        		}
        		
        	}
        }
    }
}