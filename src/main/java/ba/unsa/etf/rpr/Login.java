package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

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



}
