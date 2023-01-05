package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Domain.Tickets;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the ticket search screen. As you will notice the screen is mostly made through code. Reason for this is that I needed a
 * variable number of GridPanes (to achieve the design i wanted to, didn't find a way to do it with ListView etc. TableView wasn't used
 * because I don't like the headers. Has a somewhat large amount of code but most of it is just trivially creating buttons and such
 */
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
    public Button homeBtn;


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
        if (current == null || current.size() == 0)return;
        for (int i = 0; i < current.size(); ++i){
            String curDepCity = DaoFactory.departuresDao().getEndCity(current.get(i).getID());
            if (!curDepCity.equals(endLocationProperty().get())){
                current.remove(i);
                --i;
            }
        }
    }

    private void filterDepartures2(List<Departures> current){
        if (current == null || current.size() == 0)return;
        List<Departures> byUser = DaoFactory.departuresDao().searchByUser(userID);
        if (nullCheck(byUser))return;
        int tmp1 = current.size();
        int tmp2 = byUser.size();
        for (int i = 0; i < tmp1; ++i){
            for (int j = 0; j < tmp2; ++j){
                if (current.get(i).equals(byUser.get(j))){
                    current.remove(i); --j;
                    --tmp1;
                    if (tmp1 == 0 || i >= tmp1)return;
                }
            }
        }
    }

    private void setNoTicketsLabel(){
        Label label = new Label();
        label.setText("Sorry! No tickets from " + getStartLocation() + " to " + getEndLocation() + " are currently available. Please check later");
        label.getStyleClass().add("noTickets");
        mainbox.getChildren().add(label);
    }

    @FXML
    public void initialize(){
        homeBtn.setOnAction(actionEvent -> {
            try {
                Passengers user = DaoFactory.passengersDao().getById(userID);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/mainWindowRevamp.fxml"));
                Parent root = loader.load();
                MainWindowNewController ctrl = loader.getController();
                ctrl.setUser(user);

                Stage stage = (Stage)mainbox.getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException | StatementException e) {
                throw new RuntimeException(e);
            }

        });
        this.mainLabel.setText("All departures from " + startLocationProperty().get() + " to " + endLocationProperty().get());
        List<Departures> departuresList = DaoFactory.departuresDao().searchByStation(startLocationProperty().get());
        filterDepartures(departuresList);
        filterDepartures2(departuresList);
        if (nullCheck(departuresList)) {
            setNoTicketsLabel();
            return;
        }
        List<String> startTimes = new ArrayList<>();
        List<String> endTimes = new ArrayList<>();
        List<String> lengthList = new ArrayList<>();
        for (Departures d : departuresList){
            startTimes.add(d.getStartTime().toString());
            endTimes.add(d.getEndTime().toString());
            lengthList.add(d.getLength());
        }

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

    private boolean nullCheck(List<Departures> d){
        return (d == null || d.size() == 0);
    }

    private void ticketBuy(Departures dep){
        Tickets newTicket = new Tickets(22, 5, dep.getID(), userID);
        dep.setTicketsLeft(dep.getTicketsLeft()- 1);
        try {
            DaoFactory.departuresDao().update(dep);
            DaoFactory.ticketsDao().add(newTicket);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Ticket purchased!");
            alert.setContentText("Thank you for using our services");
            alert.showAndWait();

            //This restarts the window (not noticeable), which updates the list of departures and hides the ones which are already purchased
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/departureSearch.fxml"));
            loader.setController(new TicketSearchController(getStartLocation(), getEndLocation(), userID));
            Parent root = loader.load();
            Stage stage = (Stage)mainbox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (StatementException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("A critical error has occured while attempting to purchase ticket. Exiting now");
            alert.showAndWait();
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setMainLabel(String label){
        mainLabel.setText(label);
    }
}
