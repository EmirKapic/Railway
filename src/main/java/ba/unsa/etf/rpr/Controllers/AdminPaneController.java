package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.TrainStations;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.time.LocalDate;
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
            List<String> locations = getAllLocations(DaoFactory.trainStationsDao().getAll());
            startChoice.getItems().addAll(locations);
            endChoice.getItems().addAll(locations);

            //This piece of code turns off all cells in the datepicker before today
            datePick.setDayCellFactory(picker-> new DateCell() {
                public void updateItem(LocalDate date, boolean empty){
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(LocalDate.now()) < 0);
                }
            });

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
