
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button bStart = new Button("START");
    private ImageView cImage = new ImageView();
    private imageButton bIStart = new imageButton();
    private Image Title;
    private Image notClicked;
    private Image Clicked;
    //tempcom
    //Constructor
    public MainScreen() {
        try {
            Title = new Image(new FileInputStream("C:\\Users\\Ec_71\\Desktop\\froggerLogo.png"));
            notClicked = new Image(new FileInputStream("C:\\Users\\Ec_71\\Desktop\\buttonNClicked.png"));
            Clicked = new Image(new FileInputStream("C:\\Users\\Ec_71\\Desktop\\buttonClicked.png"));
        } catch (Exception e) {
            System.out.println("ERROR: Invalid File Path");
        }
        setStyle("-fx-background-color: darkgreen");
        setPrefSize(boardX, boardY);
        bIStart.updateImages(Clicked, notClicked);
        bIStart.layoutXProperty().bind(widthProperty().subtract(bIStart.widthProperty()).divide(2));
        bIStart.layoutYProperty().bind(heightProperty().subtract(bIStart.heightProperty()).divide(2));
       
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
   
    /* Method that checks to see if Avatar has lost all lives, then resets and goes back to
     * main menu
     * cStage -  The stage that contains the main game and the main menu
     * mainScreen - The scene that contains the mainScreen to switch back to
     * a - Avatar whose lives are checked(and stats reset if out of lives)
     */
    public void resetGame(Stage cStage,Scene mainScreen ,Avatar a)
    {
        if(a.getHealth() == 0)
        {
            cStage.setScene(mainScreen);
            a.updateHealth(3);
            a.updateScore(a.getScore()*-1);
        }
    }
}
