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
}
