package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisteringController {

    public TextField newName;
    public TextField newSurname;
    public TextField newUsername;
    public PasswordField newPass;
    public PasswordField newPassRepeat;
    public Label warningMsg1;
    public Label warningMsg2;
    public Label warningMsg3;
    public Label warningMsg4;
    public Label warningMsg5;



    public void initialize(){
        warningMsg1.setVisible(false);
        warningMsg2.setVisible(false);
        warningMsg3.setVisible(false);
        warningMsg4.setVisible(false);
        warningMsg5.setVisible(false);
    }


    public void goBackBtnClicked(ActionEvent actionEvent) {
    }

    public void registerBtnClicked(ActionEvent actionEvent) {
      if (!checkFields())return;

      if (!newPass.getText().equals(newPassRepeat.getText())){
          passwordsDontMatch();
          return;
      }

      if (usernameTaken())return;

      try {
          DaoFactory.passengersDao().add(new Passengers(5, newName.getText(), newSurname.getText(), newPass.getText(), newUsername.getText()));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Success");
          alert.setHeaderText("You have registered your account successfully. Please login now.");
          alert.showAndWait();

          Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/newLogin.fxml"));
          Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
          stage.setResizable(false);
          stage.setScene(new Scene(root));
          stage.show();

      } catch (StatementException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("A critical error has occured while trying to register a new user. Exiting now");
            alert.showAndWait();
            System.exit(1);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

    }



    private boolean checkFields(){
        boolean badInput = false;
        if (newName.getText().isEmpty()){
            warningMsg1.setVisible(true);
            badInput = true;
        }
        else warningMsg1.setVisible(false);


        if (newSurname.getText().isEmpty()){
            warningMsg2.setVisible(true);
            badInput = true;
        }
        else warningMsg2.setVisible(false);


        if (newUsername.getText().isEmpty()){
            warningMsg3.setVisible(true);
            badInput = true;
        }
        else warningMsg3.setVisible(false);


        if (newPass.getText().isEmpty()){
            warningMsg4.setVisible(true);
            badInput = true;
        }
        else warningMsg4.setVisible(false);

        if (newPassRepeat.getText().isEmpty()){
            warningMsg5.setVisible(true);
            badInput = true;
        }
        else warningMsg5.setVisible(false);


        return !badInput;
    }


    private void passwordsDontMatch(){
        warningMsg5.setVisible(true);
        warningMsg5.setText("Passwords don't match");
    }

    private boolean usernameTaken(){
        Passengers p = DaoFactory.passengersDao().getByUsername(newUsername.getText());

        if (p != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unfortunately your username is already taken. Please try a different one.");
            alert.showAndWait();
            return true;
        }
        else return false;
    }
}
