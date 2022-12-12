package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Registering {
    private Stage stage;
    private Scene scene;
    public void userRegisteredButton(ActionEvent actionEvent) throws IOException {
        //Here goes code to save user to the database
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
