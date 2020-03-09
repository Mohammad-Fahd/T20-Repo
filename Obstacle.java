package project;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
 
abstract class Obstacle extends Sprite {
    private String direction;
    private int size = 1; //DEFAULT
   
    //Constructor
    public Obstacle(int startX, int startY, Color cColor, String direction, int size) {
        super(startX, startY, cColor);
        this.direction = direction;
        this.size = size;
        super.setWidth(super.getWidth()*size);
    }
    
   //Constructor that does not take in direction or size
    public Obstacle(int startX, int startY, Color cColor) {
        super(startX, startY, cColor);
        direction = null;
    }
   
    //Moves the obstacle in a given direction
    public void move(String direction, Scene cScene) {
        if (direction.equals("R")) {
            super.moveR(cScene);
        }
        if (direction.equals("L")) {
            super.moveL(cScene);
        }
       
    }
 
    //Getters/Setters
    public String getDirection() {
        return direction;
    }
 
    public void setDirection(String direction) {
        this.direction = direction;
    }
   
    public int getSize() {
        return size;
    }
 
    public void setSize(int size) {
        this.size = size;
    }
   
    //Used by obstructions and logs
    @Override
    abstract void Interaction(Avatar a, Scene cScene);
 
}