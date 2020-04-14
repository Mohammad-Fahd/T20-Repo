import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class MainScreen extends Pane {
   
    //Instance variables
    //Determines the size of the main menu
    final int boardX = 550;
    final int boardY = 750;
   
    //variable to create buttons and title of the main menu
    private ImageView cImage = new ImageView();
    private imageButton bIStart = new imageButton();
    private Image Title;
    private Image notClicked;
    private Image Clicked;
   
    //Constructor
    public MainScreen() {
        try {
         //Attaches images to image variable
            Title = new Image(new FileInputStream("images/froggerLogo.png"));
            notClicked = new Image(new FileInputStream("images/buttonNClicked.png"));
            Clicked = new Image(new FileInputStream("images/buttonClicked.png"));
         //Throws exception when file not found ("images" file must be in correct location relative to mainScreen and AnimationAppGUI)
        } catch (Exception e) {
            System.out.println("ERROR: Invalid File Path");
        }
     //Sets colour of background
        setStyle("-fx-background-color: darkgreen");
     //Sets window size
        setPrefSize(boardX, boardY);
     //attaches image variables to start button, sets size of button
        bIStart.updateImages(Clicked, notClicked);
        bIStart.layoutXProperty().bind(widthProperty().subtract(bIStart.widthProperty()).divide(2));
        bIStart.layoutYProperty().bind(heightProperty().subtract(bIStart.heightProperty()).divide(2));
       
     //Adds buttons to Pane object, attaches image to title and sets size
        getChildren().add(bIStart);
        cImage.setImage(Title);
        cImage.setFitHeight(100);
        cImage.setFitWidth(550);
        cImage.setLayoutY(20);
        getChildren().addAll(cImage);
    }
   
    /* Method to start game once start button is pressed
     * cStage - The stage that main menu is in
     * Game - The scene that contains the main game
     */
    public void Interaction(Stage cStage, Scene Game) {
        bIStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cStage.setScene(Game);
            }
        });
    }
}
