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
    private AnchorPane controlPane;
    @FXML
    private Button translateBtn;

    @FXML
    private Button searchWordBtn;
    @FXML
    private Button libraryBtn;
    @FXML
    private Button gameCentral;
    @FXML
    private Button bookmarkBtn;

    @FXML
    public AnchorPane pane;

    @FXML
    private void setNode(Node node) {
        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    public void showComponent(String path) throws IOException {
        AnchorPane component = FXMLLoader.load(getClass().getResource(path));
        setNode(component);
    }

    void setBackgroundWhite() {
        controlPane.setStyle("-fx-background-image: url('file:C:/Users/HP/Documents/code/GIThub/Grasshopper/GrasshopperTheTepper/FrontEnd/Eapp/src/main/resources/Background/tepperWhite.png')");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateBtn.setStyle("-fx-background-color:null;");
        searchWordBtn.setStyle("-fx-background-color:null;");
        libraryBtn.setStyle("-fx-background-color:null;");
        gameCentral.setStyle("-fx-background-color:null;");
        bookmarkBtn.setStyle("-fx-background-color:null;");
        controlPane.setStyle("-fx-background-image: url('file:C:/Users/HP/Documents/code/GIThub/Grasshopper/GrasshopperTheTepper/FrontEnd/Eapp/src/main/resources/Background/tepper2.png')");
        translateBtn.setOnMouseClicked(e -> {
            setBackgroundWhite();
            try {
                showComponent("translate.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        searchWordBtn.setOnMouseClicked(e -> {
            setBackgroundWhite();
            try {
                showComponent("hello-view.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        libraryBtn.setOnMouseClicked(e -> {
            setBackgroundWhite();
            try {
                showComponent("library.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        gameCentral.setOnMouseClicked(e -> {
            setBackgroundWhite();
            try {
                showComponent("gameCentral.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}

