 
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class GOPopup extends Pane {
    final int boardX = 550;
    final int boardY = 750;
   
    private Image contBNC;
    private Image contBC;
    private Image mSBNC;
    private Image mSBC;
    private Image GOTitle;
    imageButton Continue = new imageButton();
    imageButton BacktoMS = new imageButton();
    private ImageView cImage = new ImageView();
   
    public GOPopup() {
        try {
            GOTitle = new Image(new FileInputStream("images\GOText.png"));
            contBNC = new Image(new FileInputStream("images\contBnotClicked.png"));
            contBC = new Image(new FileInputStream("images\contBClicked.png"));
            mSBNC = new Image(new FileInputStream("images\mSBnotClicked.png"));
            mSBC = new Image(new FileInputStream("images\mSBClicked.png"));
        } catch (Exception e) {
            System.out.println("ERROR: PATH NOT FOUND");
        }
        setStyle("-fx-background-color: darkgreen");
        setPrefSize(boardX, boardY);
        VBox winLayout = new VBox();
        Continue.updateImages(contBC, contBNC);
        BacktoMS.updateImages(mSBC, mSBNC);
       
        cImage.setImage(GOTitle);
        cImage.setFitHeight(100);
        cImage.setFitWidth(550);
        cImage.setLayoutY(20);
       
        winLayout.getChildren().add(Continue);
        winLayout.getChildren().add(BacktoMS);
        winLayout.layoutXProperty().bind(widthProperty().subtract(winLayout.widthProperty()).divide(2));
        winLayout.layoutYProperty().bind(heightProperty().subtract(winLayout.heightProperty()).divide(2));
       
        getChildren().add(winLayout);
        getChildren().addAll(cImage);
       
    }
   
    public void Interaction(Stage cStage, Scene mainScreen, Scene Game, Avatar a) {
        Continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cStage.setScene(Game);
                a.updateHealth(3);
                a.updateScore(a.getScore()*-1);
            }
        });
        BacktoMS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cStage.setScene(mainScreen);
                a.updateHealth(3);
                a.updateScore(a.getScore()*-1);
            }
        });
    }
}
