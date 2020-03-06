package bigPractice;

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
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = new Pane();
            root.setPrefSize(550, 750);
            Avatar avatar = new Avatar();
           
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.millis(3000),
                           new EventHandler <ActionEvent>()
                           {
                            @Override
                            public void handle(ActionEvent event)
                            {
                                avatar.updatePos();
                            }
                           }
                    )
                );
           
            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline1.setAutoReverse(true);
            timeline1.play();
           
            root.getChildren().add(avatar);
            Scene scene = new Scene(root);
            //x = 550px, y = 750px
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Frogger");
            primaryStage.show();
            avatar.setMovement(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
   
    private int CurrentX = 5;
    private int CurrentY = 14;
    //private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
    //private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    //private int[][] board = new int[15][10];
    private boolean frogObtained = false;
   
    public AnimationApp() {
    }
   
    //Prints out board as it is
    private void printCurrentState()
    {
       
    }
   
    /*
    private int[][] processAvatarMove(String movement) {
       
       
    }
    */
   
    /*
    private int[][] processObstacleMove() {
       
    }
    */
   
    //Should become private now that only 'start' is running.
    private void initialize() {
        //Scanner keyboard = new Scanner(System.in);
        String uInput = "";
       
        //System.out.println("CURRENT SCORE: " + avatar.getScore());
        //System.out.print("Please enter a direction(U,D,L,R): ");
        //uInput = keyboard.nextLine();
       
        //this.board = processObstacleMove();
        //this.board = processAvatarMove(uInput);
        printCurrentState();
    }
   
    //Not final main
    public static void main(String[] args) {
        boolean run = true;
       
        //Also launches Empty Constructor.
        //Priority --> Empty Constructor --> 'Start'
        launch(args);
       
        //This applies to collectible one (the frog (0) collectible that moves with the frog)
       
        /*
        Start.printCurrentState();
        while(run) {
            Start.initialize();
        }
        */
    }
}