package org.example.eapp;

import Model.Translate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;


public class Libraries implements Initializable {

    @FXML
    private Button story1;
    @FXML
    private Button story2;
    @FXML
    private Button story3;
    @FXML
    private Button story4;
    @FXML
    private Button story5;
    @FXML
    private WebView webView;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button mainPage;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView logoFooter;
    @FXML
    private Label listView;
    @FXML
    private Label audioBar;
    private Duration savedPosition = Duration.ZERO;

    void setAllButtonInvisible(boolean check) {
        story1.setVisible(check);
        story2.setVisible(check);
        story3.setVisible(check);
        story4.setVisible(check);
        story5.setVisible(check);
        logo.setVisible(check);
        logoFooter.setVisible(check);
        listView.setVisible(check);
        mainPage.setVisible(!check);
        pauseButton.setVisible(!check);
        playButton.setVisible(!check);
        stopButton.setVisible(!check);
        audioBar.setVisible(!check);
    }
    void onAllStoriesButton() {
        setAllButtonInvisible(false);
        webView.setVisible(true);
        webView.setPrefWidth(600);
        webView.setPrefHeight(400);
    }
    @FXML
    void onStory1ButtonClick() {
        onAllStoriesButton();
        loadHTMLintoWebView("D:\\Grasshopper\\Grasshopper\\Eapp\\data\\News\\Story1.html");
        playFullSound("D:\\Grasshopper\\Grasshopper\\Eapp\\src\\main\\resources\\Sound\\Story1.wav");
    }

    @FXML
    void onStory2ButtonClick() {
        onAllStoriesButton();
        loadHTMLintoWebView("D:\\Grasshopper\\Grasshopper\\Eapp\\data\\News\\Story2.html");
        playFullSound("D:\\Grasshopper\\Grasshopper\\Eapp\\src\\main\\resources\\Sound\\Story2.wav");
    }

    @FXML
    void onStory3ButtonClick() {
        onAllStoriesButton();
        loadHTMLintoWebView("D:\\Grasshopper\\Grasshopper\\Eapp\\data\\News\\Story3.html");
        playFullSound("D:\\Grasshopper\\Grasshopper\\Eapp\\src\\main\\resources\\Sound\\Story3.wav");
    }

    @FXML
    void onStory4ButtonClick() {
        onAllStoriesButton();
        loadHTMLintoWebView("D:\\Grasshopper\\Grasshopper\\Eapp\\data\\News\\Story4.html");
        playFullSound("D:\\Grasshopper\\Grasshopper\\Eapp\\src\\main\\resources\\Sound\\Story4.wav");
    }

    @FXML
    void onStory5ButtonClick() {
        onAllStoriesButton();
        loadHTMLintoWebView("D:\\Grasshopper\\Grasshopper\\Eapp\\data\\News\\Story5.html");
        playFullSound("D:\\Grasshopper\\Grasshopper\\Eapp\\src\\main\\resources\\Sound\\Story5.wav");
    }

    void loadHTMLintoWebView(String pathToHtml) {
        WebEngine webEngine = webView.getEngine();

        File htmlFile = new File(pathToHtml);
        webEngine.load(htmlFile.toURI().toString());

        webView.setOnMouseReleased(e -> {
            String selectedText = (String) webView.getEngine().executeScript("window.getSelection().toString()");
            if (!selectedText.isEmpty()) {
                Translate trans = new Translate("en","vi",selectedText);
                String translatedText = trans.translate();
                webView.getEngine().executeScript("document.getSelection().getRangeAt(0).deleteContents();"
                        + "document.getSelection().getRangeAt(0).insertNode(document.createTextNode('" + translatedText + "'))");
            }
        });
    }

    public void playFullSound(String filePath) {
        Media media = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        playButton.setOnAction(e -> {
            mediaPlayer.play();
            mediaPlayer.seek(savedPosition);
        });
        stopButton.setOnAction(e -> {
            savedPosition = Duration.ZERO;
            mediaPlayer.stop();
            mediaPlayer.play();
            mediaPlayer.seek(savedPosition);
        });
        pauseButton.setOnAction(e -> {
            savedPosition = mediaPlayer.getCurrentTime();
            mediaPlayer.pause();
        });
        mainPage.setOnAction(e -> {
            mediaPlayer.stop();
            setAllButtonInvisible(true);
            webView.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.setVisible(false);
        webView.setPrefWidth(1);
        webView.setPrefHeight(1);
        setAllButtonInvisible(true);
        story1.setStyle("-fx-background-color: null;");
        story2.setStyle("-fx-background-color: null;");
        story3.setStyle("-fx-background-color: null;");
        story4.setStyle("-fx-background-color: null;");
        story5.setStyle("-fx-background-color: null;");
        mainPage.setStyle("-fx-background-color: null;");
    }

}