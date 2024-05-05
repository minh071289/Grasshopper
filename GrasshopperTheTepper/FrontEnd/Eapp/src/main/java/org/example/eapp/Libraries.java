package org.example.eapp;

import Model.Translate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Libraries implements Initializable {

    @FXML
    private Button story1;
    @FXML
    private Button story2;
    @FXML
    private WebView webView;
    @FXML
    private ButtonBar helperBar;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    private Duration savedPosition = Duration.ZERO;

    void setAllButtonInvisible() {
        story1.setVisible(false);
        story2.setVisible(false);
    }
    void onAllStoriesButton() {
        setAllButtonInvisible();
        webView.setVisible(true);
        webView.setPrefWidth(600);
        webView.setPrefHeight(400);
        helperBar.setVisible(true);
    }

    @FXML
    void onStory1ButtonClick() {
        onAllStoriesButton();
        String txtFile = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\data\\News\\Story1.txt";
        String soundFile = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\src\\main\\resources\\Sound\\Story1.wav";
        loadTextFileIntoWebView(txtFile, webView);
        playFullSound(soundFile);
    }

    @FXML
    void onStory2ButtonClick() {
        onAllStoriesButton();
        String txtFile = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\data\\News\\Story2.txt";
        String soundFile = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\src\\main\\resources\\Sound\\Story1.wav";
        loadTextFileIntoWebView(txtFile, webView);
        playFullSound(soundFile);
    }

    public static void loadTextFileIntoWebView(String filePath, WebView webView) {
        WebEngine webEngine = webView.getEngine();
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String htmlContent = "<html><body><pre>" + content.toString() + "</pre></body></html>";
        webEngine.loadContent(htmlContent);

        webView.setOnMouseReleased(e -> {
            String selectedText = (String) webView.getEngine().executeScript("window.getSelection().toString()");
            if (!selectedText.isEmpty()) {
                Translate trans = new Translate(selectedText);
                String translatedText = trans.translate();
                webView.getEngine().executeScript("document.getSelection().getRangeAt(0).deleteContents();"
                        + "document.getSelection().getRangeAt(0).insertNode(document.createTextNode('" + translatedText + "'))");
            } else {
                webView.getEngine().loadContent(htmlContent);
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
        });
        pauseButton.setOnAction(e -> {
            savedPosition = mediaPlayer.getCurrentTime();
            mediaPlayer.pause();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        story1.setText("News 1");
        story2.setText("News 2");
        webView.setVisible(false);
        webView.setPrefWidth(1);
        webView.setPrefHeight(1);
        helperBar.setVisible(false);
    }

}
