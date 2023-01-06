package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.TrainStations;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

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
        try {

            startChoice.getItems().addAll(getAllLocations(DaoFactory.trainStationsDao().getAll()));
            endChoice.getItems().addAll(getAllLocations(DaoFactory.trainStationsDao().getAll()));
        } catch (StatementException e) {
            throw new RuntimeException(e);
        }
    }


    public void depAddBtn(ActionEvent actionEvent) {

    }


    private List<String> getAllLocations(List<TrainStations> stat){
        List<String> cities = new ArrayList<>();
        for (TrainStations t : stat)
            cities.add(t.getLocation());
        return cities;
    }
}
