package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindowNewController {
    private Stage stage;
    private Scene scene;
    public ImageView leftImage;
    public ImageView rightPic2;
    public ChoiceBox entryChoice;
    public ChoiceBox endChoice;
    private List<TrainStations> stations;


    private List<String> getAllLocations(List<TrainStations> stat){
        List<String> cities = new ArrayList<>();
        for (TrainStations t : stat)
            cities.add(t.getLocation());
        return cities;
    }


    public void initialize(){
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/departureSearch.fxml"));
        Parent root = loader.load();
        TicketSearchController tctrl = loader.getController();
        tctrl.setMainLabel("All tickets from " + entryChoice.getValue() + " to " + endChoice.getValue());
        stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
