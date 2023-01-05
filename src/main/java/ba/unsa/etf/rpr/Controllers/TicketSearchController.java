package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

    @FXML
    public void initialize(){
        System.out.println(userID);
        List<Departures> departuresList = DaoFactory.departuresDao().searchByStation(startLocationProperty().get());
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
            length.setText(lengthList.get(i));
            length.getStyleClass().add("length");

            start.getStyleClass().add("city");

            end.getStyleClass().add("city");

            Label lenText = new Label();
            lenText.setText("Length");
            lenText.getStyleClass().add("lenText");

            Label startDate = new Label();
            startDate.setText(departuresList.get(i).getStartDate().toString());

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
            VBox.setMargin(next, new Insets(10,0,10,0));

            mainbox.getChildren().addAll(next);
        }


    }

    public void setMainLabel(String label){
        mainLabel.setText(label);
    }
}
