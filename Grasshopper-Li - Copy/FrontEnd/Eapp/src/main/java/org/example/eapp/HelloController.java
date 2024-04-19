package org.example.eapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    Slider slider;
    @FXML
    TextField textField;
    @FXML
    private TableView<Word> table;
    @FXML
    private TableColumn<Word, String> wordColumn;
    @FXML
    private TableColumn<Word, String> meanColumn;
    private ObservableList<Word> wordList;
    @FXML
    private TextField wordText;
    @FXML
    private TextField meanText;
    static final int  INIT_VALUE = 100;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //slider happy
        slider.setValue(INIT_VALUE);
        textField.setText(String.valueOf(INIT_VALUE));
        slider.setMax(100);
        textField.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());

        //add word
        wordList = FXCollections.observableArrayList(
                new Word("Hello", "Xin chào"),
                new Word("Bye", "Tạm biệt")
        );
        wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String> ("word"));
        meanColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("mean"));
        table.setItems (wordList);
    }

    public void add (ActionEvent e) {
        Word newWord = new Word();
        newWord.setWord(wordText.getText());
        newWord.setMean(meanText.getText());
        wordList.add(newWord);
    }

    public void delete (ActionEvent e) {
        Word selected = table.getSelectionModel().getSelectedItem();
        wordList.remove(selected);
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("Tôi tự tin - Tôi làm được. Let us be your guide!");
    }
}