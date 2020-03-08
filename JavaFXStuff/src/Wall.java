import javafx.scene.Scene;
import javafx.scene.paint.Color;
 
public class Wall extends Obstacle {
    private double avatarX, avatarY;
    private static Color color = Color.GREY;
   
    public Wall (int LocationX, int LocationY) {
        super(LocationX, LocationY, color);
    }
   
    //Send avatar back to previous location if hitting a wall.
    @Override
    public void Interaction(Avatar a, Scene cScene) {
        setCLocation(a);
        if (super.withinBounds(a)) {
            a.setX(avatarX);
            a.setY(avatarY);
        }
    }
   
    public void setCLocation(Avatar a) {
        avatarX = a.getX();
        avatarY = a.getY();
    }
}