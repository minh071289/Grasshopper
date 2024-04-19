package org.example.eapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Dictionary implements Initializable {
    private static final String DATA_FILE_PATH = "C:\\Users\\HP\\Documents\\code\\tepperapp\\Grasshopper-Li\\FrontEnd\\Eapp\\data\\E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, W> data = new HashMap<>();

    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            readData();
            loadWordList();
            initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initComponents() {
        Dictionary context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    W selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getDef();
                    context.definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            W wordObj = new W(word, definition);
            data.put(word, wordObj);
        }
    }

    class W {
        private String word;
        private String def;

        public W (String word, String def) {
            this.word = word;
            this.def = def;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getDef() {
            return def;
        }

        public void setDef(String def) {
            this.def = def;
        }
    }

}