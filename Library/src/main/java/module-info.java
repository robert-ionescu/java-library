module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens sample to javafx.fxml;
    exports sample;
}