module org.example.eapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires javafx.media;
    requires java.desktop;

    opens org.example.eapp to javafx.fxml;
    exports org.example.eapp;
}