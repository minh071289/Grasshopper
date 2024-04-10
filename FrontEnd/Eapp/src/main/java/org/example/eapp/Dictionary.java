package org.example.eapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary extends Application {
    private static final String DATA_FILE_PATH = "data/E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, W> data = new HashMap<>();

    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dictionary.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 759, 509);
        stage.setTitle("LEARN ENGLISH with TEPPER");
        stage.setScene(scene);
        stage.show();

        // init components
        initComponents(scene);

        // read word list from E_V.txt
        readData();

        // load word list to the ListView
        loadWordList();

    }

    public static void main(String[] args) {
        launch();
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
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