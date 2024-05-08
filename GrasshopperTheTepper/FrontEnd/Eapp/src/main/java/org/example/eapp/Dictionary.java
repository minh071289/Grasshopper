package org.example.eapp;

import Model.TextToSpeech;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary implements Initializable {
    private static final String DATA_FILE_PATH = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\data\\E_V.txt";
    private static final String DATA_FILE_VE = "C:\\Users\\HP\\Documents\\code\\GIThub\\Grasshopper\\GrasshopperTheTepper\\FrontEnd\\Eapp\\data\\E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, Word> dataEV = new HashMap<>();
    private Map<String, Word> dataVE = new HashMap<>();
    private Trie trieEV = new Trie();
    private Trie trieVE = new Trie();
    @FXML
    private ListView<String> listView;
    @FXML
    private Button speak;
    @FXML
    private WebView definitionView;
    @FXML
    private TextField inputTextField;
    @FXML
    private Label noticeLabel;
    @FXML
    private ComboBox<String> modeBox;
    private String currentMode;

    private String text;
    ObservableList<String> modeList =
            FXCollections.observableArrayList("Vietnamese - English", "English - Vietnamese");

    @FXML
    void onSpeakButtonClick() {
        TextToSpeech textToSpeech = new TextToSpeech(text, "hl=en-us", "Mike", Integer.toString(0));
        textToSpeech.speak();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modeBox.setItems(modeList);
        noticeLabel.setText("Chọn chế độ tra cứu để học cùng Tepper nào (^O^)");
    }

    public void modeBoxChanged (ActionEvent event) {
        currentMode = modeBox.getValue();
        if (currentMode.equals("Vietnamese - English")) {
            noticeLabel.setText("Tepper sẽ cho bạn 100% sức mạnh châu chấu ");
        } else {
            noticeLabel.setText("Tepper will give you 100% grasshopper's power");
        }
        if (currentMode!=null) {
            inputTextField.setPromptText("Tra cứu " + currentMode);
            try {
                readData(modeBox.getValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            loadWordList(modeBox.getValue());
            initComponents(modeBox.getValue());
        }
    }
    public void initComponents(String currentMode) {
        if (currentMode.equals("English - Vietnamese")) {
            Dictionary context = this;
            this.listView.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        Word selectedWord = dataEV.get(newValue.trim());
                        String definition = selectedWord.getDef();
                        context.definitionView.getEngine().loadContent(definition, "text/html");
                        text = newValue;
                    }
            );
        }


        if (currentMode.equals("Vietnamese - English")) {
            Dictionary context = this;
            this.listView.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        Word selectedWord = dataVE.get(newValue.trim());
                        String definition = selectedWord.getDef();
                        context.definitionView.getEngine().loadContent(definition, "text/html");
                    }
            );
        }

    }

    public void loadWordList(String currentMode) {
        listView.getItems().clear();
        if (currentMode.equals("English - Vietnamese")) {
            this.listView.getItems().addAll(dataEV.keySet());
        }
        if (currentMode.equals("Vietnamese - English")) {
            this.listView.getItems().addAll(dataVE.keySet());
        }

    }

    public void readData(String currentMode) throws IOException {
        if (currentMode.equals("Vietnamese - English")) {
            FileReader fis = new FileReader(DATA_FILE_VE);
            BufferedReader br = new BufferedReader(fis);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String definition = SPLITTING_CHARACTERS + parts[1];
                Word wordObj = new Word(word, definition);
                dataVE.put(word, wordObj);
                trieVE.insert(word);
            }
        } else {
            FileReader fis = new FileReader(DATA_FILE_PATH);
            BufferedReader br = new BufferedReader(fis);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String definition = SPLITTING_CHARACTERS + parts[1];
                Word wordObj = new Word(word, definition);
                dataEV.put(word, wordObj);
                trieEV.insert(word);
            }
        }

    }

    public void searchWord() {
        if (modeBox.getValue().equals("Vietnamese - English")) {
            String input = inputTextField.getText();

            boolean found = trieVE.search(input);

            if (found) {
                // Lấy danh sách từ tìm thấy và thêm vào ListView
                List<String> result = trieVE.getWordsWithPrefix(input);

                // Thông báo số từ tìm được
                StringBuilder notice = new StringBuilder();
                notice.append("Tepper tìm thấy ")
                        .append(result.size())
                        .append(" từ! (😊)");
                noticeLabel.setText(notice.toString());

                // Xóa danh sách hiện tại trong ListView
                listView.getItems().clear();

                // In ra danh sách từ
                listView.getItems().addAll(result);

            } else {
                // Nếu không tìm thấy
                listView.getItems().clear();
                noticeLabel.setText("Oops... Tepper là một chấu chấu lười biếng (z_z)");
            }
        } else {
            String input = inputTextField.getText();

            boolean found = trieEV.search(input);

            if (found) {
                // Lấy danh sách từ tìm thấy và thêm vào ListView
                List<String> result = trieEV.getWordsWithPrefix(input);

                // Thông báo số từ tìm được
                StringBuilder notice = new StringBuilder();
                notice.append("Tepper found ")
                        .append(result.size())
                        .append(" word(s)! (😊)");
                noticeLabel.setText(notice.toString());

                // Xóa danh sách hiện tại trong ListView
                listView.getItems().clear();

                // In ra danh sách từ
                listView.getItems().addAll(result);

            } else {
                // Nếu không tìm thấy
                listView.getItems().clear();
                noticeLabel.setText("Oops... Tepper is a lazy grasshopper (z_z)");
            }
        }

    }
}

class Word {
    private String word;
    private String def;

    public Word(String word, String def) {
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

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isEndOfWord;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        StringBuilder builder = new StringBuilder();

        // Traverse the trie to the node representing the prefix
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                // Prefix does not exist in the trie
                return words;
            }
            builder.append(ch);
            current = current.children.get(ch);
        }

        // Perform DFS to collect words with the given prefix
        getWords(words, current, builder);
        return words;
    }

    private void getWords(List<String> words, TrieNode node, StringBuilder builder) {
        if (node.isEndOfWord) {
            words.add(builder.toString());
        }
        for (char ch : node.children.keySet()) {
            StringBuilder nextBuilder = new StringBuilder(builder); // Clone the builder
            nextBuilder.append(ch);
            getWords(words, node.children.get(ch), nextBuilder);
        }
    }
}