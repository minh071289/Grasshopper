package org.example.eapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LEARN ENGLISH WITH TEPPER");
        stage.setScene(scene);
        stage.show();
    }
}