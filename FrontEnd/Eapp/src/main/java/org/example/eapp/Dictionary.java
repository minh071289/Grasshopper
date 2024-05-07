package org.example.eapp;

import Model.TextToSpeech;
import Model.emotionTepper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Dictionary implements Initializable {
    private static final String DATA_FILE_EV = "data/E_V.txt";
    private static final String DATA_FILE_VE = "data/V_E.txt";
    private static final String BOOKMARK_FILE_PATH = "data/BookmarkList.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private final Map<String, Word> data = new HashMap<>();
    private List<String> bookmarkList  = new ArrayList<>();
    private final Trie trieEV = new Trie();
    private final Trie trieVE = new Trie();
    private final emotionTepper Tepper = new emotionTepper();
    String text ="";
    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;
    @FXML
    private TextField inputTextField;
    @FXML
    private Label noticeLabel;
    @FXML
    private ComboBox<String> modeBox;
    @FXML
    private Button bookmarkButton;
    @FXML
    private Button speechButton;
    @FXML
    private AnchorPane pane;

    ObservableList<String> modeList =
            FXCollections.observableArrayList("Vietnamese - English", "English - Vietnamese");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //lấy các mode
        modeBox.setItems(modeList);
        try {
            readData();
            readBookmarkWordFromTxtFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookmarkButton.setStyle("-fx-background-color: null");
        speechButton.setStyle("-fx-background-color: null");
        pane.setStyle("-fx-background-image: url('file:E:/code/OOP/Eapp/src/main/resources/Dictionary/background.png')");
        //Cài đặt default mode là English - Vietnamese
        noticeLabel.setText("Tepper đã sẵn sàng giúp bạn tra cứu " + Tepper.getRandomHappyEmotion());
        modeBox.setValue("English - Vietnamese");
        inputTextField.setPromptText("Tra cứu English - Vietnamese");
        listView.getItems().clear();
        initComponents();

    }

    public void modeBoxChanged(ActionEvent event) {
        String currentMode;
        currentMode = modeBox.getValue();
        if (currentMode.equals("Vietnamese - English")) {
            noticeLabel.setText("Tepper sẽ cho bạn 100% sức mạnh châu chấu " + Tepper.getRandomHappyEmotion());
        } else {
            noticeLabel.setText("Tepper will give you 100% grasshopper's power " + Tepper.getRandomHappyEmotion());
        }

        //Thực hiện tra cứu theo từng chế độ được chọn
        inputTextField.setPromptText("Tra cứu " + currentMode);
        listView.getItems().clear(); //Mỗi lần chọn chế độ mới, danh sách từ sẽ để trống
        initComponents();
        inputTextField.clear();

    }

    public void initComponents() {
        // Get word và get definition của data tương ứng mỗi language modes
        Dictionary context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null && !newValue.trim().isEmpty()) {
                        Word selectedWord = data.get(newValue.trim());
                        text = newValue.trim();
                        String definition = selectedWord.getDef();
                        if (!bookmarkList.contains(selectedWord.getWord()+definition)) {
                            bookmarkButton.setStyle("-fx-background-color: null;");
                        } else {
                            bookmarkButton.setStyle("-fx-background-color: #FFD700;");
                        }
                        context.definitionView.getEngine().loadContent(definition, "text/html");

                    } else {
                        // Xử lý trường hợp không có từ nào được chọn
                        // xóa nội dung hiển thị
                        bookmarkButton.setStyle("-fx-background-color: null;");
                        context.definitionView.getEngine().loadContent("", "text/html");
                    }
                }
        );
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_VE);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
            trieVE.insert(word);
        }

        FileReader fir = new FileReader(DATA_FILE_EV);
        BufferedReader bfr = new BufferedReader(fir);
        String line2;
        while ((line2 = bfr.readLine()) != null) {
            String[] parts = line2.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
            trieEV.insert(word);
        }

    }

    public void readBookmarkWordFromTxtFile() throws IOException {
        FileReader fis = new FileReader(BOOKMARK_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            bookmarkList.add(line);
        }
    }

    @FXML
    void onSpeakButtonClick() {
        if (!Objects.equals(text, "")) {
            if (modeBox.getValue().equals("English - Vietnamese")) {
                TextToSpeech speech = new TextToSpeech(text, "hl=en-us", "Linda", Integer.toString(0));
                speech.speak();
                text = "";
            } else if (modeBox.getValue().equals("Vietnamese - English")) {
                TextToSpeech speech = new TextToSpeech(text, "hl=vi-vn", "Chi", Integer.toString(0));
                speech.speak();
                text = "";
            }
        }

    }
    public void onBMclick() {
        Word selectedWord = data.get(listView.getSelectionModel().getSelectedItem());
        if (selectedWord!=null) {
            if (!bookmarkList.contains(selectedWord.getWord()+selectedWord.getDef())) {
                addBookmarkWord(selectedWord);
                bookmarkButton.setStyle("-fx-background-color: #FFD700;");
                noticeLabel.setText("Tepper đã lưu từ '" + selectedWord.getWord() + "' cho bạn vào Bookmark rùi " + Tepper.getRandomHappyEmotion());
            } else {
                removeBookmarkWord(selectedWord);
                bookmarkButton.setStyle("-fx-background-color: null;");
                noticeLabel.setText("Bạn đã xóa từ '" + selectedWord.getWord() + "' trong Bookmarkkkk " + Tepper.getRandomSadEmotion());
            }
        }


    }

    /**
     * add word to set and export to file BookmarkList.txt.
     *
     * @param word word.
     */
    public void addBookmarkWord(Word word) {
        if (!bookmarkList.contains(word.getWord() + word.getDef())) {
            bookmarkList.add(word.getWord() + word.getDef());
            try {
                File file = new File(BOOKMARK_FILE_PATH);
                FileWriter fileWriter = new FileWriter(BOOKMARK_FILE_PATH, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(word.getWord() + word.getDef());
                printWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void removeBookmarkWord(Word word) {
        bookmarkList.remove(word.getWord()+word.getDef());
        try {
            File file = new File(BOOKMARK_FILE_PATH);
            FileWriter fileWriter = new FileWriter(BOOKMARK_FILE_PATH, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String word1 : bookmarkList) {
                printWriter.println(word1);
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchWord() {
        String notice = "";
        if (modeBox.getValue().equals("Vietnamese - English")) {
            String input = inputTextField.getText();
            if (input.isEmpty()) {
                listView.getItems().clear();
                noticeLabel.setText("Tiếp tục tra cứu nào " + Tepper.getRandomHappyEmotion());
            } else {
                // Lấy danh sách từ tìm thấy và thêm vào ListView
                List<String> result = trieVE.getWordfound(input);
                if (result == null) {
                    notice += "Tepper sẽ ra biển tìm từ bạn cần, tham khảo một số từ bên dưới nhé "
                            + Tepper.getRandomSadEmotion();
                    noticeLabel.setText(notice);
                } else {
                    // Thông báo số từ tìm được
                    notice += "Tepper tìm thấy " + result.size() + " từ! " + Tepper.getRandomHappyEmotion();
                    noticeLabel.setText(notice);

                    // Xóa danh sách hiện tại trong ListView
                    listView.getItems().clear();

                    // In ra danh sách từ
                    listView.getItems().addAll(result);
                }
            }
        } else {
            String input = inputTextField.getText();
            if (input.isEmpty()) {
                listView.getItems().clear();
                noticeLabel.setText("Let's keep looking up " + Tepper.getRandomHappyEmotion());
            } else {
                // Lấy danh sách từ tìm thấy và thêm vào ListView
                List<String> result = trieEV.getWordfound(input);
                if (result == null) {
                    notice += "Oops...Tepper is having a brain glitch " + Tepper.getRandomSadEmotion();
                    noticeLabel.setText(notice);
                } else {
                    // Thông báo số từ tìm được
                    notice += "Tepper found " + result.size() + " word(s)! " + Tepper.getRandomHappyEmotion();
                    noticeLabel.setText(notice);

                    // Xóa danh sách hiện tại trong ListView
                    listView.getItems().clear();

                    // In ra danh sách từ
                    listView.getItems().addAll(result);
                }
            }
        }
    }

}

class Trie {
    private final Map<Character, Trie> letter; // Lưu kí tự của nó và cây tiếp theo
    private String word;// Lưu từ được hình thành khi xét đến nó
    private boolean end = false;// Check xem kết thúc từ chưa

    public Trie() {
        this(null);
    }

    private Trie(String word) {
        this.word = word;
        letter = new HashMap<>();
    }

    /**
     * Thêm kí tự vào Trie.
     */
    private void add(char character) {
        String s;
        if (this.word == null) s = Character.toString(character);
        else s = this.word + character;
        letter.put(character, new Trie(s));
    }

    /**
     * Thêm từ vào Trie.
     */
    public void insert(String diagnosis) {
        Trie node = this;
        for (char c : diagnosis.toCharArray()) {
            if (!node.letter.containsKey(c)) node.add(c);
            node = node.letter.get(c);
        }
        node.end = true;
    }

    /**
     * Kiểm tra các từ chứa kí tự được tìm kiếm có trong cây không  và đưa ra danh sách
     */
    public List<String> getWordfound(String prefix) {
        Trie trieNode = this;
        for (char c : prefix.toCharArray()) {
            if (!trieNode.letter.containsKey(c)) return null;
            trieNode = trieNode.letter.get(c);
        }
        return trieNode.allPrefixes();
    }

    /**
     * Lấy tất cả từ có chứa prefix ra.
     */
    private List<String> allPrefixes() {
        List<String> diagnosisResults = new ArrayList<>();
        if (this.end) {
            diagnosisResults.add(this.word);
        }
        // Lấy key và value ra
        for (Map.Entry<Character, Trie> entry : letter.entrySet()) {
            Trie child = entry.getValue();
            // Dùng đệ quy tìm các từ con
            Collection<String> childPrefixes = child.allPrefixes();
            diagnosisResults.addAll(childPrefixes);
        }
        return diagnosisResults;
    }
}

