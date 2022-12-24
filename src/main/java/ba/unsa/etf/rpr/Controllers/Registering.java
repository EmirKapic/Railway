package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.PassengersDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Exceptions.StatementException;
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
    public void userRegisteredButton(ActionEvent actionEvent) throws IOException, StatementException {
        flag = true;
        PassengersDaoSQLImpl psql = new PassengersDaoSQLImpl();
        Passengers newUser = psql.getByUsername(newUsername.getText());
        emptyCheck();
        if (flag) passwordsMatchingCheck();
        if (flag && newUser != null)reportProblem("Korisničko ime je već zauzeto. Izaberite neko drugo.");


        if (flag){
            newUser = new Passengers(0, newName.getText(), newSurname.getText(), newPassword.getText(), newUsername.getText());
            psql.add(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspjesna registracija");
            alert.setHeaderText("Uspjesno ste se registrovali. Unesite sada vase podatke.");
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/login.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }
}
