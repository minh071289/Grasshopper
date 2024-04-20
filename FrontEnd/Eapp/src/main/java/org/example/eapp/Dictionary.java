package org.example.eapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary implements Initializable {
    private static final String DATA_FILE_PATH = "data/E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, Word> data = new HashMap<>();
    private Trie trie = new Trie();

    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;
    @FXML
    private TextField inputTextField;
    @FXML
    private Label noticeLabel;

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
                    Word selectedWord = data.get(newValue.trim());
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
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
            trie.insert(word);
        }
    }

    public void searchWord() {
        String input = inputTextField.getText();
        if (input.isEmpty()) {
            listView.getItems().clear();
            noticeLabel.setText("Searching...");
            return;
        }

        boolean found = trie.search(input);

        if (found) {
            // Lấy danh sách từ tìm thấy và thêm vào ListView
            List<String> result = trie.getWordsWithPrefix(input);

            // Thông báo số từ tìm được
            StringBuilder notice = new StringBuilder();
            notice.append("We found ")
                    .append(result.size())
                    .append(" word(s)!");
            noticeLabel.setText(notice.toString());

            // Xóa danh sách hiện tại trong ListView
            listView.getItems().clear();

            // In ra danh sách từ
            listView.getItems().addAll(result);

        } else {
            // Nếu không tìm thấy
            listView.getItems().clear();
            noticeLabel.setText("Try again");
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
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return words; // Tiền tố không tồn tại
            }
            current = current.children.get(ch);
        }
        // Duyệt từ nút hiện tại để lấy danh sách từ
        getWords(words, current, new StringBuilder(prefix));
        return words;
    }

    private void getWords(List<String> words, TrieNode node, StringBuilder builder) {
        if (node.isEndOfWord) {
            words.add(builder.toString());
        }
        for (char ch : node.children.keySet()) {
            builder.append(ch);
            getWords(words, node.children.get(ch), builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}


