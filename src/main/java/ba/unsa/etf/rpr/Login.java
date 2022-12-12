package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    private Stage stage;
    private Scene scene;
    public PasswordField passwordField;
    public TextField usernameField;

    public void loginButtonClick(ActionEvent actionEvent) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR);
            emptyField.setTitle("Greska");
            emptyField.setHeaderText("Korisničko ime i lozinka ne mogu biti prazni! Unesite ponovo");
            emptyField.showAndWait();
        }

    }
    public void registerButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/registering.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
