package project;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
 
public class Logs extends Obstacle {
    private int respawnX, despawnX;
    private static Color color = Color.BROWN;
    private static int size = 3;
    private int LR;
    
    //Constructor(Determines movement direction of Log on creation)
    public Logs (int startingX, int startingY, String direction) {
        super(startingX, startingY, Color.BROWN, direction, size);
        if (direction.equals("L")) {
            LR = -50;
            respawnX = 600;
            despawnX = -150;
        } else {
            LR = 50;
            respawnX = -150;
            despawnX = 600;
        }
    }
   
    /*
     * Move alongside log while within bounds.
     */
    @Override
    public void Interaction(Avatar a, Scene cScene) {
        if (super.withinBounds(a)) {
            super.move(super.getDirection(), cScene);
            if (super.getDirection() == "L") {
                a.setX(a.getX()-super.getMoveLen());
            } else {
                a.setX(a.getX()+super.getMoveLen());
            }
        } else {
            super.move(super.getDirection(), cScene);
        }
        resetLog();
    }
   
    //When log reaches the end of the screen, respawns on the other side
    public void resetLog() {
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
    
    public int getLR()
    {
    	return LR;
    }
   
}