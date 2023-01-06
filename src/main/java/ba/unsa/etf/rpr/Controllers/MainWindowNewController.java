package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.TrainStationsDAOSQLImpl;
import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Domain.TrainStations;
import ba.unsa.etf.rpr.Exceptions.StatementException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindowNewController {
    public Label warningMsg3;
    public Label warningMsg1;
    public Label warningMsg2;
    private Stage stage;
    private Scene scene;
    public ImageView leftImage;
    public ImageView rightPic2;
    public ChoiceBox entryChoice;
    public ChoiceBox endChoice;
    private List<TrainStations> stations;

    private Passengers user;

    public void setUser(Passengers user) {
        this.user = user;
    }

    private List<String> getAllLocations(List<TrainStations> stat){
        List<String> cities = new ArrayList<>();
        for (TrainStations t : stat)
            cities.add(t.getLocation());
        return cities;
    }


    public void initialize() throws StatementException {
        warningMsg1.setVisible(false);
        warningMsg2.setVisible(false);
        warningMsg3.setVisible(false);

        Image image = new Image("Pictures/railwaytrain.png");
        leftImage.setImage(image);
        image = new Image("Pictures/rightpic2.jpg");
        rightPic2.setImage(image);

        TrainStationsDAOSQLImpl tsql = new TrainStationsDAOSQLImpl();
        stations = tsql.getAll();

        List<String> locations = getAllLocations(stations);

        entryChoice.getItems().addAll(locations);
        endChoice.getItems().addAll(locations);
    }

    public void searchButtonClicked(ActionEvent actionEvent) throws IOException {
        if (!fieldCheck())return;
        if (!checkStations())return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/departureSearch.fxml"));
        loader.setController(new TicketSearchController(entryChoice.getValue().toString(), endChoice.getValue().toString(), user.getID()));
        Parent root = loader.load();
        stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void myTicketsClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/myTicketsWindow.fxml"));
            loader.setController(new MyTicketsController(user.getID()));
            stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean fieldCheck(){
        boolean badInput = false;
        if (entryChoice.getValue() == null){
            warningMsg1.setVisible(true);
            badInput = true;
        }
        else warningMsg1.setVisible(false);

        if (endChoice.getValue() == null){
            warningMsg2.setVisible(true);
            badInput = true;
        }
        else warningMsg2.setVisible(false);

        return !badInput;
    }

    private boolean checkStations(){
        if(entryChoice.getValue() != endChoice.getValue()) {
            warningMsg3.setVisible(false);
            return true;
        }
        else {
            warningMsg3.setVisible(true);
            return false;
        }
    }
}
