 
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
//Class that handles Game Error and checking to see if the Avatar has run out of lives
public class GOPopup extends Pane {
 //Width and height of Game Over Popup
    final int boardX = 550;
    final int boardY = 750;
   
 //Images that are used to create the game over screen
    private Image contBNC;
    private Image contBC;
    private Image mSBNC;
    private Image mSBC;
    private Image GOTitle;
 
 //Buttons to handle restarting or going back to main menu
    imageButton Continue = new imageButton();
    imageButton BacktoMS = new imageButton();
    private ImageView cImage = new ImageView();
   
 //Constructor
    public GOPopup() {
        try {
         //Sets image variables to correct file path
            GOTitle = new Image(new FileInputStream("images/GOText.png"));
            contBNC = new Image(new FileInputStream("images/contBnotClicked.png"));
            contBC = new Image(new FileInputStream("images/contBClicked.png"));
            mSBNC = new Image(new FileInputStream("images/mSBnotClicked.png"));
            mSBC = new Image(new FileInputStream("images/mSBClicked.png"));
         //Throws error if file path is not found (file path is relative so must be in correct location compared to AnimationAppGUI)
        } catch (Exception e) {
            System.out.println("ERROR: PATH NOT FOUND");
        }
     //Sets colour of background
        setStyle("-fx-background-color: darkgreen");
     //Sets size of window
        setPrefSize(boardX, boardY);
     //Places game over screen in a vertical box object
        VBox winLayout = new VBox();
     //Sets images for going back to main menu or restarting
        Continue.updateImages(contBC, contBNC);
        BacktoMS.updateImages(mSBC, mSBNC);
     //Sets width and height of title  
        cImage.setImage(GOTitle);
        cImage.setFitHeight(100);
        cImage.setFitWidth(550);
        cImage.setLayoutY(20);
       
     //Adds buttons to vertical box object
        winLayout.getChildren().add(Continue);
        winLayout.getChildren().add(BacktoMS);
        winLayout.layoutXProperty().bind(widthProperty().subtract(winLayout.widthProperty()).divide(2));
        winLayout.layoutYProperty().bind(heightProperty().subtract(winLayout.heightProperty()).divide(2));
       
        getChildren().add(winLayout);
        getChildren().addAll(cImage);
       
    }
   
 /* Activates when player is dead (has 0 lives) to show game over screen. Then handles button presses
  * cStage - the stage that contains game over, main screen, and the main game
  * mainScreen - the scene containing the main menu
  * a - The avatar whose stats get reset when Interaction is triggered
  */
    public void Interaction(Stage cStage, Scene mainScreen, Scene Game, Avatar a) {
        Continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             //Go back to game
                cStage.setScene(Game);
             //Reset player stats
                a.updateHealth(3);
                a.updateScore(a.getScore()*-1);
            }
        });
        BacktoMS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             //Go back to main screen
                cStage.setScene(mainScreen);
             //Reset player stats
                a.updateHealth(3);
                a.updateScore(a.getScore()*-1);
            }
        });
    }
}
