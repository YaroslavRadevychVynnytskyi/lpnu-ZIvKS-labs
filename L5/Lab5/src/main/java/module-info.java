module sample.lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.lab5 to javafx.fxml;
    exports sample.lab5;
}