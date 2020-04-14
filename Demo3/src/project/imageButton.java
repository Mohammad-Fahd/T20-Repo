import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
 
//tempcom
public class imageButton extends Button {
 
    public void updateImages(final Image clicked, final Image notClicked) {
     //Adds image to button for when it is not clicked
        ImageView cButton = new ImageView(notClicked);
        this.getChildren().add(cButton);
     //Sets background to be transparent
        this.setStyle("-fx-background-color: transparent;");
     //When pressed, changes button to a different image indicating that it has been clicked
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                cButton.setImage(clicked);
            }
        });
       
     //When button is released, goes back to "unclicked" image
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                cButton.setImage(notClicked);
            }
        });
       
        super.setGraphic(cButton);
    }
}
