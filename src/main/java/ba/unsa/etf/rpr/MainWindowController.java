package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class MainWindowController {
    public Button ReservationButton;
    public Button DeparturesButton;


    public BorderPane mainWindowPane;
    public ImageView TrainPic;

    @FXML
    public void initialize(){
        Image image = new Image("train2.jpg");
        TrainPic.setImage(image);
    }
}
