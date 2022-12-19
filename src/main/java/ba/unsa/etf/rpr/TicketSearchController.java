package ba.unsa.etf.rpr;

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

public class TicketSearchController {
    public Label mainLabel;
    public VBox mainbox;

    @FXML
    public void initialize(){
        Label time = new Label();
        time.setText("08:30");
        time.getStyleClass().add("time");
        //time.setFont(new Font("Century", 29));

        Label time2 = new Label();
        time2.setText("15:00");
        time2.getStyleClass().add("time");
        //time2.setFont(new Font("Century", 29));

        Label length = new Label();
        length.setText("2h50min");
        length.getStyleClass().add("length");


        Label start = new Label();
        start.setText("Sarajevo");
        start.getStyleClass().add("city");


        Label end = new Label();
        end.setText("Mostar");
        end.getStyleClass().add("city");

        Label lenText = new Label();
        lenText.setText("Length");
        lenText.getStyleClass().add("lenText");

        /*HBox upper = new HBox();
        upper.getChildren().addAll(time,length, time2);
        upper.setSpacing(120);



        HBox lower = new HBox();
        lower.getChildren().addAll(start, lenText, end);
        lower.setSpacing(145);

        VBox nextOne = new VBox();
        nextOne.getChildren().addAll(upper,lower);
        VBox.setMargin(nextOne, new Insets(10, 0 , 0 , 10));
        nextOne.getStyleClass().add("departure");*/

        GridPane next = new GridPane();
        next.setHgap(90);
        next.getStyleClass().add("departure");
        next.add(time,0,0);
        next.add(length, 1, 0);
        next.add(time2, 2, 0);
        next.add(start, 0, 1);
        next.add(lenText, 1, 1);
        next.add(end,2,1);
        VBox.setMargin(next, new Insets(10,0,10,0));




        mainbox.getChildren().addAll(next);

    }

    public void setMainLabel(String label){
        mainLabel.setText(label);
    }
}
