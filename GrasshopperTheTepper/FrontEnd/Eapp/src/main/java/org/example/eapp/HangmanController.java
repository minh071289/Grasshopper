package org.example.eapp;

import Model.Hangman;
import Model.HangmanWordlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HangmanController extends GameCentralController implements Initializable {
    @FXML
    private GridPane keyboard;
    @FXML
    private GridPane answer;
    @FXML
    private Label hint;
    @FXML
    private ImageView imageHangman;
    @FXML
    private ImageView hangmanMain;

    Hangman myHangman;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] keyLabels = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z",
                "X", "C", "V", "B", "N", "M", "\uD83D\uDCA1"
        };
        int row = 0, col = 0;
        keyboard.setHgap(2.5);
        keyboard.setVgap(2.5);
        keyboard.setAlignment(Pos.CENTER);

        keyboard.getChildren().forEach(node -> GridPane.setHalignment(node, HPos.CENTER));
        myHangman = new Hangman(HangmanWordlist.getWordDefinitions());
        setWordToGuess(myHangman.getKey().length());

        for (String label : keyLabels) {
            Button button = new Button(label);
            button.setStyle(
                    "-fx-background-color: black;"+
                            "-fx-effect: dropshadow(gaussian, rgba(255,165,0,0.8), 5, 0, 0, 1)" +
                            "dropshadow(gaussian, rgba(255,165,0,0.8), 5, -1, 0, 1);" +
                            "-fx-background-radius: 5px;" +
                            "-fx-font-size: 10px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: orange;" +
                            "-fx-padding: 5px 10px;" +
                            "-fx-background-insets: 3px;"
            );
            button.setPrefWidth(32);
            button.setPrefHeight(32);
            button.setAlignment(Pos.CENTER);
            if(!button.getText().equals("\uD83D\uDCA1")) {
                button.setOnAction(event -> {
                    keyPressEventHandler(label);
                    hangmanMain.setVisible(false);
                    button.setVisible(false);
                });
            } else {
                button.setOnAction(event -> {
                    hint.setVisible(true);
                    hangmanMain.setVisible(false);
                    hint.setText(myHangman.getHint());
                    button.setVisible(false);
                });
            }
            keyboard.add(button, col, row);
            col++;
            if (col >= 9) {
                col = 0;
                row++;
            }
        }
        hint.setAlignment(Pos.CENTER);
        answer.setAlignment(Pos.CENTER);
        hint.setVisible(false);
    }
    public void setWordToGuess(int n) {
        for(int i=0; i<n; ++i) {
            Label label = new Label("_");
            label.setStyle("-fx-font-size: 35;");
            label.setPadding(new Insets(5,5,0,0));
            answer.add(label, i, 0);
        }
    }
    void keyPressEventHandler (String key) {
        myHangman.atGuess(key);
        setCurrentWordForGridPane(myHangman.getWordToGuess());
        setImageForWrongGuess(myHangman.getWrongGuess());
        if(myHangman.isOver() || myHangman.isWin()) {
            String path = "file:D:\\learnWithTepper\\FrontEnd\\Eapp\\src\\main\\resources\\Hangman\\lose.png";
            if(myHangman.isWin()) {
                path = "file:D:\\learnWithTepper\\FrontEnd\\Eapp\\src\\main\\resources\\Hangman\\win.png";
            } else {
                for(int i=0; i<myHangman.getKey().length(); ++i) {
                    if(((Label) answer.getChildren().get(i)).getText().equals("_")) {
                        ((Label) answer.getChildren().get(i)).setText(String.valueOf(myHangman.getKey().charAt(i)));
                    }
                }
            }
            imageHangman.setImage(new Image(path));
        }
    }
    void setCurrentWordForGridPane(List<Character> listChar) {
        int index = 0;
        for (Character c : listChar) {
            String tmp = String.valueOf(c);
            ((Label) answer.getChildren().get(index)).setText(tmp);
            index++;
        }
    }
    void setImageForWrongGuess(int i) {
        String path = "file:D:\\learnWithTepper\\FrontEnd\\Eapp\\src\\main\\resources\\Hangman\\roach" +
                i + ".png";
        Image newImage = new Image(path);
        imageHangman.setImage(newImage);
    }

}
