package org.example.eapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import Model.TextToSpeech;
import Model.Translate;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Translator implements Initializable {
    @FXML
    private ComboBox<String> langFrom;
    @FXML
    private ComboBox<String> langTo;
    @FXML
    private TextArea textFrom;
    @FXML
    private TextArea textTo;
    @FXML
    private Button transButton;
    @FXML
    private Button speak1;
    @FXML
    private Button speak2;
    private String currentLang;
    private String transLang;
    private static final int MAX_CHARACTER_COUNT = 600;
    ObservableList<String> langList = FXCollections.observableArrayList("Vietnamese", "English");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        langFrom.setItems(langList);
        langTo.setItems(langList);
        transButton.setDisable(true);
        textFrom.setOnKeyTyped(event -> {
            boolean isTextEmpty = textFrom.getText().trim().isEmpty();
            boolean lackLanguageSelection = currentLang == null || transLang == null;
            transButton.setDisable(isTextEmpty || lackLanguageSelection);
        });
        textFrom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > MAX_CHARACTER_COUNT) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                if (currentLang.equals("Vietnamese")) {
                    alert.setTitle("Cảnh báo");
                    alert.setContentText("Vượt quá số kí tự cho phép!");
                } else {
                    alert.setTitle("Warning");
                    alert.setContentText("Maximum character count exceeded!");
                }
                alert.showAndWait();

                textFrom.setText(newValue.substring(0, MAX_CHARACTER_COUNT));
            }
        });
    }

    public void currentLanguageChanged(ActionEvent event) {
        currentLang = langFrom.getValue();
        if (currentLang.equals("Vietnamese")) {
            langTo.setValue("English");
            textFrom.setPromptText("Vui lòng nhập không quá 600 kí tự");
        }
        if (currentLang.equals("English")) {
            langTo.setValue("Vietnamese");
            textFrom.setPromptText("Please enter no more than 600 characters");
        }
    }

    public void targetLanguageChanged(ActionEvent event) {
        transLang = langTo.getValue();
        if(transLang.equals("English")) {
            langFrom.setValue("Vietnamese");
            textFrom.setPromptText("Vui lòng nhập không quá 600 kí tự");
        }
        if (transLang.equals("Vietnamese")) {
            langFrom.setValue("English");
            textFrom.setPromptText("Please enter no more than 600 characters");
        }
    }

    @FXML
    private void onTransButtonClick(ActionEvent e) throws IOException {
        String text = textFrom.getText();
        if (langFrom.getValue().equals("Vietnamese")) {
            textTo.setText(translate("vi", "en", text));
        } else {
            textTo.setText(translate("en", "vi", text));
        }
    }

    @FXML
    public void tts1() {
        if (currentLang.equals("English")) {
            TextToSpeech pronounce = new TextToSpeech(textFrom.getText(), "hl=en-us", "Mike", Integer.toString(0));
            pronounce.speak();
        } else {
            TextToSpeech pronounce = new TextToSpeech(textFrom.getText(), "hl=vi-vn", "Chi", Integer.toString(0));
            pronounce.speak();
        }
    }

    @FXML
    public void tts2() {
        if (transLang.equals("English")) {
            TextToSpeech pronounce = new TextToSpeech(textTo.getText(), "hl=en-us", "Mike", Integer.toString(0));
            pronounce.speak();
        } else {
            TextToSpeech pronounce = new TextToSpeech(textTo.getText(), "hl=vi-vn", "Chi", Integer.toString(0));
            pronounce.speak();
        }
    }
    private static String translate(String languageFrom, String languageTo, String text) {
        Translate trans = new Translate(languageFrom, languageTo, text);
        return trans.translate();
    }
}