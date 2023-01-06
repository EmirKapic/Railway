package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.PassengersDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Passengers;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public Button loginButton;
    private Stage stage;
    private Scene scene;
    public PasswordField passwordField;
    public TextField usernameField;

    private void incorrectUsername(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Neispravni podaci");
        alert.setHeaderText("Korisničko ime ili lozinka nije ispravna. Pokušajte ponovo");
        alert.showAndWait();
    }

    private void emptyField(){
        Alert emptyField = new Alert(Alert.AlertType.ERROR);
        emptyField.setTitle("Greska");
        emptyField.setHeaderText("Korisničko ime i lozinka ne mogu biti prazni! Unesite ponovo");
        emptyField.showAndWait();
    }

    public void loginButtonClick(ActionEvent actionEvent) throws IOException {
        //The error checking is horribly done here, but it works and I don't have time to make it prettier
        boolean correctFlag = true;

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            correctFlag = false;
            emptyField();
        } //Questionable but lets keep it

        Passengers user = DaoFactory.passengersDao().getByUsername(usernameField.getText());



        if (correctFlag && (user == null || !user.getPassword().equals(passwordField.getText()))) {
            correctFlag = false;
            incorrectUsername();
        }

        if (correctFlag){
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();

            Parent root = null;
            if (usernameField.getText().equals("admin")){
                root = FXMLLoader.load(getClass().getResource("/FXMLFiles/adminPanel.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setResizable(false);
                newStage.show();
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/mainWindowRevamp.fxml"));
            root = loader.load();
            MainWindowNewController ctrl = loader.getController();
            ctrl.setUser(user);


            Stage newStage = new Stage();
            scene = new Scene(root);
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.show();
        }
    }
    public void registerButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/newRegister.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
