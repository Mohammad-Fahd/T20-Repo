
import javafx.scene.Scene;
import javafx.scene.paint.Color;
 
public class Logs extends Obstacle {
    private int respawnX, despawnX;
    private static Color color = Color.BROWN;
    private static int size = 3;
    private int LR;
    
    //Constructor(Determines movement direction of Log on creation)
    public Logs (int startingX, int startingY, String direction) {
        super(startingX, startingY, direction,size);
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
   
    /* Move alongside log while within bounds.
     * a - The avatar to influence
     */
    @Override
    public void Interaction(Avatar a) {
        if (super.withinBounds(a)) {
            super.move(super.getDirection());
            if (super.getDirection() == "L") {
                a.setX(a.getX()-super.getMoveLen());
            } else {
                a.setX(a.getX()+super.getMoveLen());
            }
        } else {
            super.move(super.getDirection());
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
    
    // Method that returns the LR instance variable, which determines the direction the log should move in
    public int getLR()
    {
    	return LR;
    }
   
}
