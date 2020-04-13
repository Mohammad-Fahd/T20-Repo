package project;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
 
public class Obstruction extends Obstacle {
    private int respawnX, despawnX;
    private static Color color = Color.ORANGE;
    private int LR;
   
    //Constructor(Determines size, movement direction)
    public Obstruction (int startingX, int startingY, String direction, int size) {
        super(startingX, startingY, direction, size);
        if (direction.equals("L")) {
            LR = -50;
            //if (size.euqal(2))
            if (size==2) {
            	respawnX = 550;
                despawnX = -200;
                //this.color = setFill(Color.ORANGE);
            } else if (size==1) {
            	//Not working with this, change in future
            	respawnX = 700;
                despawnX = -50;
                //this.color = setFill(RED);
            }
            
        } else {
        	if (size==2) {
        		respawnX = -100;
        		despawnX = 650;
        		//this.color = setFill(ORANGE);
        	}else if (size==1) {
        		respawnX = -50;
        		despawnX = 700;
        		//this.color = setFill(RED);
        	}
        }
    }
    
    /*
     * Move alongside log while within bounds.
     * a - The avatar whose position is checked and moved
     */
    
    @Override
    public void Interaction(Avatar a) {
        if (super.withinBounds(a)) {
            super.move(super.getDirection());
            a.resetPos();
            a.updateHealth(a.getHealth()-1);
        } else {
            super.move(super.getDirection());
        }
        resetObstruction();
    }
    
    //When obstruction reaches the end of the screen, it respawns on the other side 
    public void resetObstruction() {
        String cDirect = super.getDirection();
        if (cDirect.equals("R")) {
            if (super.getX() > despawnX) {
                super.setX(respawnX);
            }
        } else {
            if (super.getX() < despawnX) {
                super.setX(respawnX);
            }
        }
    }
}