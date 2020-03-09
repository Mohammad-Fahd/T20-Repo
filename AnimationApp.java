package project;
import java.util.ArrayList;
 
import java.util.Arrays;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class AnimationApp extends Application {
    ArrayList<Sprite> objects = new ArrayList<Sprite>();
    final int boardX = 550;
    final int boardY = 750;
   
   
    @Override
    public void start(Stage primaryStage) {
        try {
        	//Creation of window
            Pane root = new Pane();
            root.setPrefSize(550, 750);
            root.setStyle("-fx-background-color: lightgreen");
            Avatar avatar = new Avatar();
            createObstacles();
            createEnds();
            Collectible collectible = new Collectible(250,200,300,1);
            objects.add(collectible);
            for (Sprite i: objects) 
            {
                root.getChildren().add(i);
            }
            root.getChildren().add(avatar);
            Scene scene = new Scene(root,550,750);
            avatar.setMovement(scene);
           
            //Timeline that moves non-player characters and detects collisions
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.millis(17),
                           new EventHandler <ActionEvent>()
                           {
                            @Override
                            public void handle(ActionEvent event)
                            {
                                avatar.collision(objects,scene);
                                for (Sprite i : objects) {
                                    i.Interaction(avatar, scene);
                                }
                                avatar.checkGame(root);
                            }
                           }
                    )
                );
           
            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline1.setAutoReverse(true);
            timeline1.play();
            //x = 550px, y = 750px
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Frogger");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
   
   
    public AnimationApp() {
    }
   
    //Used to have objects with adjacent Y values alternate direction of movement
    private boolean switchLR(boolean LR) {
        if (LR) {
            return(false);
        } else {
            return(true);
        }
    }
   
    //initializes Obstructions,Logs,Walls
   private void createObstacles() {
       boolean LeftRight = true;
       String Left = "L";
       String Right = "R";
       //Y = 650, 700, 350 -- EMPTY
       //Y = 0, 50 -- U Shaped Walls
       //Y = 100, 150, 200, 250, 300 -- LOGS
       //Y = 400, 450, 500, 550, 600 -- OBSTRUCTIONS (Vehicles)
       //0, 250, 500 R500
       //0, 200, 400 R400
       for(int i = 0; i < boardY+50; i += 50) {
           if (i >= 0 && i <= 50) {
               for(int ix = 0; ix < boardX+50; ix += 50) {
                   if (i == 0) {
                       objects.add(new Wall(ix, i));
                   } else {
                       objects.add(new Wall(ix, i));
                       ix += 50;
                   }
               }
               
           }
           if (i > 50 && i < 350) {
               for(int ix = 0; ix < boardX+150; ix += 50) {
                   if (ix == 0 || ix == 250 || ix == 500) {
                       if (LeftRight) {
                           objects.add(new Logs(ix, i, Left));
                       } else {
                           objects.add(new Logs(ix, i, Right));
                       }
                    }
                   if (ix == 150 || ix == 400 || ix == 650) {
                	   if (LeftRight) {
                		   Obstruction a = new Obstruction(ix, i, Left, 2);
                		   a.changeColor(Color.BLUE);
                           objects.add(a);
                       } else {
                    	   Obstruction a = new Obstruction(ix, i, Right, 2);
                		   a.changeColor(Color.BLUE);
                           objects.add(a);
                       }
                   }
                   
                }
               LeftRight = switchLR(LeftRight);
           }
         //0, 200, 400 R400
           if (i > 350 && i < 650) {
        	   for(int ix = 0; ix < boardX+50; ix += 50) {
        		   if (ix == 0 || ix == 200 || ix == 400 ) {
        			   if (LeftRight) {
                           objects.add(new Obstruction(ix, i, Left,2));
                       } else {
                           objects.add(new Obstruction(ix, i, Right,2));
                       }
        		   }
        	   }  
        	   LeftRight = switchLR(LeftRight);
           }
       }
   }
   

   //Creates the end goals
   public void createEnds()
   {
	   for(int ix = 50; ix < boardX+50; ix += 100)
	   {
	       objects.add(new Collectible(ix,50,600,4));
	   }
   }
   
   
    //Not final main
    public static void main(String[] args) {
        launch(args);
       
    }
}