
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Wall extends Obstacle {
    private double avatarX, avatarY;
    private static Color color = Color.GREY;
   
    //Constructor
    public Wall (int LocationX, int LocationY) {
        super(LocationX, LocationY, 50);
    }
   
    //Send avatar back to previous location if hitting a wall.
    @Override
    public void Interaction(Avatar a) {
        setCLocation(a);
        if (super.withinBounds(a)) {
            a.setX(avatarX);
            a.setY(avatarY);
        }
    }
   
    //Test method
    public void setCLocation(Avatar a) {
        avatarX = a.getX();
        avatarY = a.getY();
    }
}
