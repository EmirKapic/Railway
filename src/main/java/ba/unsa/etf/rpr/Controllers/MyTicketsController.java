package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTicketsController {
    public int userID;

    public MyTicketsController(int userID){
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    List<Departures> departuresList;
    public VBox mainbox;
    public Button homeBtn;


    public void initialize(){
        departuresList = DaoFactory.departuresDao().searchByUser(userID);
        //Add case for when user has no tickets right now
        List<String> startTimes = new ArrayList<>();
        List<String> endTimes = new ArrayList<>();
        List<String> lengthList = new ArrayList<>();
        List<String> startLocation = new ArrayList<>();
        List<String> endLocation = new ArrayList<>();

        for (Departures d : departuresList){
            startTimes.add(d.getStartTime().toString());
            endTimes.add(d.getEndTime().toString());
            lengthList.add(d.getLength());
            startLocation.add(DaoFactory.departuresDao().getStartCity(d.getID()));
            endLocation.add(DaoFactory.departuresDao().getEndCity(d.getID()));

        }
        for (int i = 0; i < departuresList.size(); ++i){
            Label start = new Label(); Label end = new Label();
            start.setText(startLocation.get(i)); end.setText(endLocation.get(i));
            Label time = new Label();
            time.setText(startTimes.get(i));
            time.getStyleClass().add("time");

            Label time2 = new Label();
            time2.setText(endTimes.get(i));
            time2.getStyleClass().add("time");

            Label length = new Label();
            String[] tmp = lengthList.get(i).split(":");
            length.setText(tmp[0] + "h " + tmp[1] + "min");
            length.getStyleClass().add("length");

            start.getStyleClass().add("city");

            end.getStyleClass().add("city");

            Label lenText = new Label();
            lenText.setText("Length");
            lenText.getStyleClass().add("lenText");

            Label startDate = new Label();
            startDate.setText(departuresList.get(i).getStartDate().toString().split("T")[0]);
            startDate.getStyleClass().add("dateLabel");

            /*
            Button buyBtn = new Button();
            buyBtn.setText("Buy now!");
            buyBtn.setPadding(new Insets(10, 10, 10, 10));
            int finalI = i;
            buyBtn.setOnAction(actionEvent -> ticketBuy(departuresList.get(finalI)));
             */

            Button undoBtn = new Button();
            undoBtn.setText("Undo purchase");
            undoBtn.setPadding(new Insets(10, 10, 10, 10));
            int finalI = i;
            undoBtn.setOnAction(actionEvent -> ticketUndo(departuresList.get(finalI)));

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

        homeBtn.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/mainWindowRevamp.fxml"));
                Parent root = loader.load();
                MainWindowNewController ctrl = loader.getController();
                ctrl.setUser(DaoFactory.passengersDao().getById(userID));
                Stage stage = (Stage)mainbox.getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException | StatementException e) {
                throw new RuntimeException(e);
            }

        });

    }


    private void ticketUndo(Departures d){

    }


}

