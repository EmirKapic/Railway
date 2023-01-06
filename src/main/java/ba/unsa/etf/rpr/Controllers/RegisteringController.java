package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Passengers;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
