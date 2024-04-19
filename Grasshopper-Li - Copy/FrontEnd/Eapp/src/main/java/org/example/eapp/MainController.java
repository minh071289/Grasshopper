package org.example.eapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button translateBtn;

    @FXML
    private Button searchWordBtn;

    @FXML
    private AnchorPane pane;

    @FXML
    private void setNode(Node node) {
        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    private void showComponent(String path) throws IOException {
        AnchorPane component = FXMLLoader.load(getClass().getResource(path));
        setNode(component);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateBtn.setOnMouseClicked(e -> {
            try {
                showComponent("translate.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        searchWordBtn.setOnMouseClicked(e -> {
            try {
                showComponent("hello-view.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}

