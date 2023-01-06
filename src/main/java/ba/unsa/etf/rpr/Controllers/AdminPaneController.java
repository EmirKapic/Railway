package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminPaneController {

    public ChoiceBox startChoice;
    public ChoiceBox endChoice;
    public DatePicker datePick;
    public TextField startTime;
    public TextField endTime;
    public TextField length;
    public TextField ticketsAvailable;
    public Label warningMsg1;
    public Label warningMsg2;
    public Label warningMsg3;


    public void initialize(){
        warningMsg1.setVisible(false);
        warningMsg2.setVisible(false);
        warningMsg3.setVisible(false);
    }


    public void depAddBtn(ActionEvent actionEvent) {

    }
}
