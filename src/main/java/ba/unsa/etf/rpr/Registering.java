package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class Registering {
    public TextField newName;
    public TextField newSurname;
    public TextField newUsername;
    public PasswordField newPassword;
    public PasswordField newPasswordRepeat;
    private Stage stage;
    private Scene scene;
    private boolean flag = true;

    private void reportProblem(String problem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        flag = false;
        alert.setTitle("Greska pri registraciji");
        alert.setHeaderText(problem);
        alert.showAndWait();
    }

    private void emptyCheck(){
        if(newName.getText().isEmpty() || newPassword.getText().isEmpty() || newSurname.getText().isEmpty() || newPasswordRepeat.getText().isEmpty() || newUsername.getText().isEmpty()){
            reportProblem("Polja ne smiju ostati prazna!");
        }
    }
    private void passwordsMatchingCheck(){
        if (!newPassword.getText().equals(newPasswordRepeat.getText())){
            reportProblem("Lozinke se ne poklapaju! Unesite ponovo");
        }
    }
    public void userRegisteredButton(ActionEvent actionEvent) throws IOException {
        flag = true;
        PassengersDaoSQLImpl psql = new PassengersDaoSQLImpl();
        Passengers newUser = psql.getByUsername(newUsername.getText());
        emptyCheck();
        if (flag) passwordsMatchingCheck();
        if (flag && newUser != null)reportProblem("Korisničko ime je već zauzeto. Izaberite neko drugo.");

        //Here goes code to save user to the database
        if (flag){
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
