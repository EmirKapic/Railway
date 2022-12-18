package ba.unsa.etf.rpr;

import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainWindowNewController {
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
        Image image = new Image("railwaytrain.png");
        leftImage.setImage(image);
        image = new Image("rightpic2.jpg");
        rightPic2.setImage(image);

        TrainStationsDAOSQLImpl tsql = new TrainStationsDAOSQLImpl();
        stations = tsql.getAll();

        List<String> locations = getAllLocations(stations);

        entryChoice.getItems().addAll(locations);
        endChoice.getItems().addAll(locations);
    }
}
