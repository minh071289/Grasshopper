package org.example.eapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameCentralController implements Initializable {
    @FXML
    private Button game1;
    @FXML
    private Button game2;
    @FXML
    public Button to;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private void setNode(Node node) {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }
    public void showComponent(String path) throws IOException {
        AnchorPane component = FXMLLoader.load(getClass().getResource(path));
        setNode(component);
    }

    @FXML
    void onToHomeClick() {
        System.out.println("cit");
        game1.setVisible(true);
        game2.setVisible(true);
        anchorPane.setPrefHeight(1);
        anchorPane.setPrefWidth(1);
        anchorPane.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        to.setStyle("-fx-background-color:null;");
        game1.setStyle("-fx-background-color:null;");
        game2.setStyle("-fx-background-color:null;");
        to.setVisible(false);

        game1.setOnMouseClicked(e -> {
            anchorPane.setPrefWidth(570);
            anchorPane.setPrefHeight(374);
            anchorPane.setVisible(true);
            game1.setVisible(false);
            game2.setVisible(false);
            try {
                showComponent("game.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        game2.setOnMouseClicked(e -> {
            anchorPane.setPrefWidth(570);
            anchorPane.setPrefHeight(374);
            anchorPane.setVisible(true);
            game1.setVisible(false);
            game2.setVisible(false);
            try {
                showComponent("dinosaur.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
