package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Domain.TrainStations;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public Label warningMsg4;
    public Label warningMsg5;
    public Label warningMsg6;
    public Label warningMsg7;


    public void initialize(){
        warningMsg1.setVisible(false);
        warningMsg2.setVisible(false);
        warningMsg3.setVisible(false);
        warningMsg4.setVisible(false);
        warningMsg5.setVisible(false);
        warningMsg6.setVisible(false);
        warningMsg7.setVisible(false);
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

        if (!checkFields())return;

        if (!checkStations())return;





        Departures d = setNewDeparture();
        System.out.println(d);

    }

    /**
     * Checks if any of the fields are empty
     * @return true if everything is okay, false otherwise
     */
    private boolean checkFields(){
        boolean badInput = false;
        if (!checkTime(startTime.getText())){
            warningMsg1.setVisible(true);
            badInput = true;
        }
        else warningMsg1.setVisible(false);
        if (!checkTime(endTime.getText())) {
            warningMsg2.setVisible(true);
            badInput = true;
        }
        else warningMsg2.setVisible(false);
        if (!checkTime(length.getText())){
            warningMsg3.setVisible(true);
            badInput = true;
        }
        else warningMsg3.setVisible(false);

        String tcktsA = ticketsAvailable.getText();
        try{
            if (tcktsA == null || tcktsA.isEmpty() || Integer.parseInt(tcktsA) > 100){
                warningMsg4.setVisible(true);
                warningMsg4.setText("Maximum number of tickets is 100");
                badInput = true;
            }
            else warningMsg4.setVisible(false);
        }
        catch(NumberFormatException e){
            warningMsg4.setVisible(true);
            warningMsg4.setText("Please enter a whole number");
            badInput = true;
        }

        if (startChoice.getValue() == null){
            warningMsg5.setVisible(true);
            badInput = true;
        }
        else warningMsg5.setVisible(false);

        if (endChoice.getValue() == null){
            warningMsg6.setVisible(true);
            badInput = true;
        }
        else warningMsg6.setVisible(false);

        if (datePick.getValue() == null){
            warningMsg7.setVisible(true);
            badInput = true;
        }
        else warningMsg7.setVisible(false);



        return !badInput;
    }

    private boolean checkTime(String time){
        return time.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }
    private boolean checkStations(){
        if (startChoice.getValue() == null || endChoice.getValue() == null)return false;
        else if (startChoice.getValue() == endChoice.getValue()){
            Alert sameChoice = new Alert(Alert.AlertType.ERROR);
            sameChoice.setTitle("Error");
            sameChoice.setHeaderText("Start and end station can't be the same one");
            sameChoice.showAndWait();
            return false;
        }
        else return true;

    }

    private Departures setNewDeparture(){

        LocalDate start = datePick.getValue();
        LocalDate end = datePick.getValue();

        if (onTwoDays()) end = end.plusDays(1);

        int startH = Integer.parseInt(startTime.getText().split(":")[0]);
        int startMin = Integer.parseInt(startTime.getText().split(":")[1]);

        int endH = Integer.parseInt(endTime.getText().split(":")[0]);
        int endMin = Integer.parseInt(endTime.getText().split(":")[1]);

        int betweenH = endH-startH;
        if (betweenH < 0)betweenH+=24;

        int betweenMin = endMin - startMin;
        if (betweenMin < 0) {
            betweenH--;
            betweenMin += 60;
        }

        return new Departures(5, LocalDateTime.of(start, LocalTime.of(startH, startMin)), LocalDateTime.of(end, LocalTime.of(endH, endMin)),
                betweenH + ":" + betweenMin + ":00",
                Integer.parseInt(ticketsAvailable.getText()), 1, 2, Integer.parseInt(ticketsAvailable.getText()), " ", " ");
    }

    private boolean onTwoDays(){
        return Integer.parseInt(startTime.getText().split(":")[0]) > Integer.parseInt(endTime.getText().split(":")[0]);
    }


    private List<String> getAllLocations(List<TrainStations> stat){
        List<String> cities = new ArrayList<>();
        for (TrainStations t : stat)
            cities.add(t.getLocation());
        return cities;
    }
}
