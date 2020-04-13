import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
 
//tempcom
public class imageButton extends Button {
 
    public void updateImages(final Image clicked, final Image notClicked) {
        ImageView cButton = new ImageView(notClicked);
        this.getChildren().add(cButton);
        this.setStyle("-fx-background-color: transparent;");
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                cButton.setImage(clicked);
            }
        });
       
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                cButton.setImage(notClicked);
            }
        });
       
        super.setGraphic(cButton);
    }
}
