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
 
public class AnimationAppGUI extends Application {
    ArrayList<Box> objects = new ArrayList<Box>();
    ArrayList<Sprite> visual = new ArrayList<Sprite>();
    final int boardX = 550;
    final int boardY = 750;
   
   
    @Override
    public void start(Stage primaryStage) {
        try {
        	//Creation of window
            Pane root = new Pane();
            MainScreen mS = new MainScreen();
            root.setPrefSize(boardX, boardY);
            root.setStyle("-fx-background-color: lightgreen");
            Avatar avatar = new Avatar(350,700);
            Sprite player = new Sprite(avatar);
            GameUI userInterface = new GameUI(avatar);
            createObstacles();
            createEnds();
            Collectible collectible = new Collectible(250,200,300,1);
            Sprite pickup = new Sprite(collectible);
            objects.add(collectible);
            visual.add(pickup);
            for (Sprite i: visual) 
            {
                root.getChildren().add(i);
            }
            root.getChildren().add(player);
            userInterface.establishLabels(root);
            Scene scene = new Scene(root,550,750);
            Scene mScene = new Scene(mS,550,750);
            avatar.setMovement(scene);
            objects.add(avatar);
            visual.add(player);
           
            //Timeline that moves non-player characters and detects collisions
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.millis(17),
                           new EventHandler <ActionEvent>()
                           {
                            @Override
                            public void handle(ActionEvent event)
                            {
                                mS.Interaction(primaryStage, scene);
                            	for (Box i : objects) {
                                    i.Interaction(avatar);
                                }
                                if(avatar.getX() == 350 && avatar.getY() == 700)
                                {
                                	collectible.resetCollectible(objects);
                                }
                                for(int i = 0;i < visual.size(); i++)
                                {
                                	visual.get(i).setX(objects.get(i).getX());
                                	visual.get(i).setY(objects.get(i).getY());
                                	if(objects.get(i) instanceof Collectible)
                                	{
                                	visual.get(i).changeColor(((Collectible)objects.get(i)).getColor());
                                	}
                                }
                                userInterface.updateLabels();
                                mS.resetGame(primaryStage, mScene, avatar);
                            }
                           }
                    )
                );
           
            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline1.setAutoReverse(true);
            timeline1.play();
            //x = 550px, y = 750px
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(mScene);
            primaryStage.setTitle("Frogger");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
   
   
    public AnimationAppGUI() {
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
                   if (i == 0) 
                   {
                	   Wall w = new Wall(ix,i);
                	   Sprite s = new Sprite(w);
                       objects.add(w);
                	   visual.add(s);
                   } else {
                	   Wall w = new Wall(ix,i);
                	   ix += 50;
                	   Sprite s = new Sprite(w);
                       objects.add(w);
                	   visual.add(s);
                   }
               }
               
           }
           if (i > 50 && i < 350) {
               for(int ix = 0; ix < boardX+150; ix += 50) {
                   if (ix == 0 || ix == 250 || ix == 500) {
                       if (LeftRight) 
                       {
                    	   Logs l = new Logs(ix,i,Left);
                    	   Sprite s = new Sprite(l);
                           objects.add(l);
                           visual.add(s);
                       } else {
                    	   Logs l = new Logs(ix,i,Right);
                    	   Sprite s = new Sprite(l);
                           objects.add(l);
                           visual.add(s);
                       }
                    }
                   if (ix == 150 || ix == 400 || ix == 650) {
                	   if (LeftRight) {
                		   Obstruction a = new Obstruction(ix, i, Left, 2);
                		   Sprite s = new Sprite(a);
                		   s.changeColor(Color.BLUE);
                           objects.add(a);
                           visual.add(s);
                       } else {
                    	   Obstruction a = new Obstruction(ix, i, Right, 2);
                    	   Sprite s = new Sprite(a);
                		   s.changeColor(Color.BLUE);
                           objects.add(a);
                           visual.add(s);
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
                		   Obstruction a = new Obstruction(ix, i, Left, 2);
                		   Sprite s = new Sprite(a);
                		   s.changeColor(Color.ORANGE);
                           objects.add(a);
                           visual.add(s);
                       } else {
                    	   Obstruction a = new Obstruction(ix, i, Right, 2);
                    	   Sprite s = new Sprite(a);
                		   s.changeColor(Color.ORANGE);
                           objects.add(a);
                           visual.add(s);
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
		   Collectible endpoint = new Collectible(ix,50,600,4);
		   Sprite s = new Sprite(endpoint);
	       objects.add(endpoint);
	       visual.add(s);
	       
	   }
   }
   
   
    //Not final main
    public static void main(String[] args) {
        launch(args);
       
    }
}