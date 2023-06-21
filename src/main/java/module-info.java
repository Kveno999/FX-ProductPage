module com.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    opens productpage to javafx.fxml;
    exports productpage;
    exports productpage.databaseutils;
    exports productpage.models;
    opens productpage.models to javafx.fxml;
}