import java.util.ArrayList;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.Duration;
 
//Controller that combines logic classes w/ GUI classes
public class AnimationAppGUI extends Application {
 //Creates arrayLists to store multiple Boxes (logic class) and Sprites (GUI class) 
    ArrayList<Box> objects = new ArrayList<Box>();
    ArrayList<Sprite> visual = new ArrayList<Sprite>();
 
 //variables that stores size of window
    final int boardX = 550;
    final int boardY = 750;
   
   
    @Override
    public void start(Stage primaryStage) {
        try {
            //Creation of window
            Pane root = new Pane();
         
         //Creates main menu object that shows at start and when player goes back
            MainScreen mS = new MainScreen();
         
         //Creates game over object that shows when player's health reaches 0
            GOPopup GameOver = new GOPopup();
         
         //Sets size of window
            root.setPrefSize(boardX, boardY);
         
         //Sets background colour of window
            root.setStyle("-fx-background-color: lightgreen");
         
         //Creates avatar (logic) class and attaches sprite to it to translate movements onto window
            Avatar avatar = new Avatar(350,700);
            Sprite player = new Sprite(avatar);
         
         //Creates GameUI object that shows player score and health on top left
            GameUI userInterface = new GameUI(avatar);
         
         //Creates obsctacles and end goals as shown in methods below
            createObstacles();
            createEnds();
         
         //Creates collectible in the middle of "river" level and attaches sprite to it
            Collectible collectible = new Collectible(250,200,300,1);
            Sprite pickup = new Sprite(collectible);
         
         //Adds river collectible to arrayLists (collectible class in objects, sprite class in visual)
            objects.add(collectible);
            visual.add(pickup);
         
         //Adds all sprites in visual arrayList to screen
            for (Sprite i: visual)
            {
                root.getChildren().add(i);
            }
         
         //Adds player to screen (drawn last so as to be displayed on top of all other objects)
            root.getChildren().add(player);
            userInterface.establishLabels(root);
         
         //Creates scenes for main game, main menu, and game over screen
            Scene scene = new Scene(root,550,750);
            Scene mScene = new Scene(mS,550,750);
            Scene GOScreen = new Scene(GameOver, 550, 750);
         
         //Checks for keyboard input and moves player character accordingly
            avatar.setMovement(scene);
         
         //Adds avatar to arrayLists
            objects.add(avatar);
            visual.add(player);
           
            //Timeline that moves non-player characters and detects collisions, as well as button presses on main menu and game over screen
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.millis(17),
                           new EventHandler <ActionEvent>()
                           {
                            @Override
                            public void handle(ActionEvent event)
                            {
                             
                             //Checks for button presses on main menu screen
                                mS.Interaction(primaryStage, scene);
                             
                             //Looks through each box object and checks to see if it has collided with avatar
                             //Then performs appropriate action as defined in their classes
                                for (Box i : objects) {
                                    i.Interaction(avatar);
                                }
                             
                             //Resets collectibles when player ends up back at the start
                                if(avatar.getX() == 350 && avatar.getY() == 700)
                                {
                                    collectible.resetCollectible(objects);
                                }
                             
                             //Changes x and y of GUI classes according to the positions determined in logic classes
                             //Also applies any colour changes to collectibles
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
                             
                             //Checks to see if player has lost all their lives, then goes to game over screen
                                if(avatar.getHealth() == 0) {
                                    primaryStage.setScene(GOScreen);
                                    GameOver.Interaction(primaryStage, mScene, scene, avatar);
                                }
                            }
                           }
                    )
                );
           
         //Plays timeline
            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline1.setAutoReverse(true);
            timeline1.play();
            //x = 550px, y = 750px
         
         //Sets game to be displayed on main screen
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
