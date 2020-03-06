package bigPractice;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
        setWidth(moveLen);
        setHeight(moveLen);
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
            
                    System.out.println("UP");
                    break;
                case RIGHT:
                    setX(getX() + moveLen);
                    
                    System.out.println(getX());
                    System.out.println(getX() + moveLen);
                    System.out.println("RIGHT");
                    break;
                case DOWN:
                    setY(getY() + moveLen);
                   
                    System.out.println("DOWN");
                    break;
                case LEFT:
                    setX(getX() - moveLen);
                  
                    System.out.println(getX() - moveLen);
                    System.out.println("LEFT");
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
   
    //Resets position of the frog
    public Avatar resetPos() {
        final int originX = 5;
        final int originY = 14;
        int cScore = this.score;
        return(new Avatar(originX, originY, cScore));
    }
}