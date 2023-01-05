package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Domain.Tickets;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.control.spreadsheet.Grid;

import java.util.ArrayList;
import java.util.List;

public class TicketSearchController {
    public Label mainLabel;
    private int userID;

    public TicketSearchController(String startLocation, String endLocation, int userID) {
        this.startLocation.set(startLocation);
        this.endLocation.set(endLocation);
        this.userID = userID;
    }

    private SimpleStringProperty startLocation = new SimpleStringProperty(" !");
    private SimpleStringProperty endLocation = new SimpleStringProperty(" !");

    private List<Departures> departuresList;

    public VBox mainbox;


    public String getStartLocation() {
        return startLocation.get();
    }

    public SimpleStringProperty startLocationProperty() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation.set(startLocation);
    }

    public String getEndLocation() {
        return endLocation.get();
    }

    public SimpleStringProperty endLocationProperty() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation.set(endLocation);
    }


    private void filterDepartures(List<Departures> current){
        for (int i = 0; i < current.size(); ++i){
            String curDepCity = DaoFactory.departuresDao().getEndCity(current.get(i).getID());
            if (!curDepCity.equals(endLocationProperty().get())){
                current.remove(i);
            }
        }
    }

    @FXML
    public void initialize(){
        List<Departures> departuresList = DaoFactory.departuresDao().searchByStation(startLocationProperty().get());
        filterDepartures(departuresList);
        List<String> startTimes = new ArrayList<>();
        List<String> endTimes = new ArrayList<>();
        List<String> lengthList = new ArrayList<>();
        for (Departures d : departuresList){
            startTimes.add(d.getStartTime().toString());
            endTimes.add(d.getEndTime().toString());
            lengthList.add(d.getLength());
        }
        this.mainLabel.setText("All departures from " + startLocationProperty().get() + " to " + endLocationProperty().get());
        for (int i = 0; i < departuresList.size(); ++i){
            Label start = new Label(); Label end = new Label();
            start.setText(startLocationProperty().get()); end.setText(endLocationProperty().get());
            Label time = new Label();
            time.setText(startTimes.get(i));
            time.getStyleClass().add("time");

            Label time2 = new Label();
            time2.setText(endTimes.get(i));
            time2.getStyleClass().add("time");

            Label length = new Label();
            String[] tmp = lengthList.get(i).split(":");
            length.setText(tmp[0] + ":" + tmp[1]);
            length.getStyleClass().add("length");

            start.getStyleClass().add("city");

            end.getStyleClass().add("city");

            Label lenText = new Label();
            lenText.setText("Length");
            lenText.getStyleClass().add("lenText");

            Label dateLabel = new Label();
            dateLabel.setText("Date");
            dateLabel.getStyleClass().add("city");

            Label startDate = new Label();
            startDate.setText(departuresList.get(i).getStartDate().toString().split("T")[0]);
            startDate.getStyleClass().add("dateLabel");



            Button buyBtn = new Button();
            buyBtn.setText("Buy now!");
            buyBtn.setPadding(new Insets(10, 10, 10, 10));
            int finalI = i;
            buyBtn.setOnAction(actionEvent -> ticketBuy(departuresList.get(finalI)));

            GridPane next = new GridPane();
            next.setHgap(90);
            next.getStyleClass().add("departure");
            next.add(time,0,0);
            next.add(length, 1, 0);
            next.add(time2, 2, 0);
            next.add(startDate, 3, 0);
            next.add(start, 0, 1);
            next.add(lenText, 1, 1);
            next.add(end,2,1);
            next.add(dateLabel, 3, 1);
            next.add(buyBtn, 4, 0, 1, 2);
            VBox.setMargin(next, new Insets(10,0,10,0));

            mainbox.getChildren().addAll(next);
        }


    }

    private void ticketBuy(Departures dep){
        Tickets newTicket = new Tickets(22, 5, dep.getID(), userID);
        try {
            DaoFactory.ticketsDao().add(newTicket);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Ticket purchased!");
            alert.setContentText("Thank you for using our services");
            alert.showAndWait();
        } catch (StatementException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("A critical error has occured while attempting to purchase ticket. Exiting now");
            alert.showAndWait();
            System.exit(1);
        }

    }


    public void setMainLabel(String label){
        mainLabel.setText(label);
    }
}
