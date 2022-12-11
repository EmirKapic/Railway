module Railway {
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;

    requires org.controlsfx.controls;

    opens ba.unsa.etf.rpr to javafx.fxml;
    exports ba.unsa.etf.rpr;
}