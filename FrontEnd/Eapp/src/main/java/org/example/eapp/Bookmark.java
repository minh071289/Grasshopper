package org.example.eapp;


import Model.emotionTepper;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.media.Media;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Bookmark implements Initializable {
    @FXML
    Slider slider;
    @FXML
    TextField textField;
    private MediaPlayer mediaPlayer;
    private PauseTransition pauseTransition;
    private boolean isSoundPlaying = false;
    static final int INIT_VALUE = 100;

    private static final String BOOKMARK_FILE_PATH = "data/BookmarkList.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, Word> data  = new HashMap<>();
    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;
    @FXML
    private TextField wordText;
    @FXML
    private TextField meanText;
    @FXML
    private Label notice;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    private final emotionTepper Tepper = new emotionTepper();

    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setStyle("-fx-background-image: url('file:E:/code/OOP/Eapp/src/main/resources/BOOKMARK/background.png')");
        addButton.setStyle("-fx-background-color: null;");
        editButton.setStyle("-fx-background-color: null;");
        deleteButton.setStyle("-fx-background-color: null;");
        try {
            readData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadWordList();
        initComponents();

        slider.setValue(INIT_VALUE);
        textField.setText(String.valueOf(INIT_VALUE));
        slider.setMax(100);
        textField.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());
        // Khởi tạo PauseTransition với thời gian delay 3 giây
        pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> {
            // Phát âm thanh khi pauseTransition kết thúc
            playSound();
        });

        // Thêm sự kiện cho slider
        slider.setOnMouseReleased(event -> {
            stopSound();
            // Kiểm tra nếu slider dừng lại
            if (!slider.isValueChanging()) {
                // Bắt đầu pauseTransition khi slider được thả ra
                pauseTransition.playFromStart();
            }
        });
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                stopSound();
                // Bắt đầu pauseTransition khi nhập xong
                pauseTransition.playFromStart();
            }
        });
    }

    private void playSound() {
        // Lấy giá trị của slider để xác định âm thanh cần phát
        double value = slider.getValue();
        String soundFile = "";

        // Chọn âm thanh tương ứng dựa trên giá trị của slider
        if (value >= 75) {
            soundFile = "E:/code/OOP/Eapp/src/main/resources/fightingSound/Sound75.mp3";
        } else if (value >= 45) {
            soundFile = "E:/code/OOP/Eapp/src/main/resources/fightingSound/Sound45.mp3";
        } else {
            soundFile = "E:/code/OOP/Eapp/src/main/resources/fightingSound/Sound0.mp3";
        }

        // Phát âm thanh
        playFullSound(soundFile);
        isSoundPlaying = true;
    }

    private void playFullSound(String soundFile) {
        // Khởi tạo đối tượng MediaPlayer và phát âm thanh
        Media media = new Media(new File(soundFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void stopSound() {
        // Dừng âm thanh
        if (isSoundPlaying) {
            mediaPlayer.stop();
            isSoundPlaying = false;
        }

    }

    public void initComponents() {
        Bookmark context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null && !newValue.trim().isEmpty()) {
                        Word selectedWord = data.get(newValue.trim());
                        String definition = selectedWord.getDef();
                        context.definitionView.getEngine().loadContent(definition, "text/html");
                    } else {
                        context.definitionView.getEngine().loadContent("", "text/html");
                    }
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(BOOKMARK_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
        }
    }

    public void addButton() {
        Word newWord = new Word();
        newWord.setWord(wordText.getText().trim());
        newWord.setDef("<html>"+meanText.getText().trim());
        if (!(newWord.getWord().trim().isEmpty()) && !Objects.equals(newWord.getDef().trim(), "<html>")) {
            if (data.containsKey(newWord.getWord())) {
                notice.setText("Có từ '" + newWord.getWord().trim() +"' trong Bookmark rùi bạn ơi " + Tepper.getRandomSadEmotion());
            } else {
                addBookmarkWord(newWord);
                listView.getItems().add(newWord.getWord().trim());
                notice.setText("Tepper đã thêm từ '" + newWord.getWord().trim() + "' rùi nhé" + Tepper.getRandomHappyEmotion());
            }
        }
        wordText.clear();
        meanText.clear();

    }

    public void addBookmarkWord(Word word) {
        if (!data.containsKey(word.getWord())) {
            data.put(word.getWord().trim(), word);
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

    public void deleteButton() {
        Word selectedWord = data.get(listView.getSelectionModel().getSelectedItem());
        if (selectedWord != null) {
            removeBookmarkWord(selectedWord);
            notice.setText("Tepper đã xóa từ '" + selectedWord.getWord() + "' rùi " + Tepper.getRandomSadEmotion());
            listView.getItems().clear();
            loadWordList();
        }

    }

    public void removeBookmarkWord(Word word) {
        data.remove(word.getWord());
        try {
            File file = new File(BOOKMARK_FILE_PATH);
            FileWriter fileWriter = new FileWriter(BOOKMARK_FILE_PATH, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String word1 : data.keySet()) {
                printWriter.println(word1+data.get(word1).getDef());
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editButton() {
        Word selectedWord = data.get(listView.getSelectionModel().getSelectedItem());
        if (selectedWord != null) {
            Word newWord = new Word();
            newWord.setWord(wordText.getText().trim());
            newWord.setDef("<html>"+meanText.getText().trim());
            if (!(newWord.getWord().trim().isEmpty()) && !Objects.equals(newWord.getDef().trim(), "<html>")) {
                if (data.containsKey(newWord.getWord())) {
                    notice.setText("Có từ '" + newWord.getWord().trim() +"' trong Bookmark rùi bạn ơi " + Tepper.getRandomSadEmotion());
                } else {
                    removeBookmarkWord(selectedWord);
                    addBookmarkWord(newWord);
                    listView.getItems().add(newWord.getWord().trim());
                    notice.setText("Tepper đã sửa '" + selectedWord.getWord() + "' thành '" + newWord.getWord() + "' rùi " + Tepper.getRandomHappyEmotion());
                }
            }
            listView.getItems().clear();
            loadWordList();
            wordText.clear();
            meanText.clear();
        }

    }
}







