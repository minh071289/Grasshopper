package org.example.eapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewDemo extends Application {
    @Override
    public void start(Stage stage) {
        TableView<UserAccount> table = new TableView<>();

        // Tạo cột Word (Kiểu dữ liệu String)
        TableColumn<UserAccount, String> wordCol = new TableColumn<>("Word");

        // Tạo cột Mean (Kiểu dữ liệu String)
        TableColumn<UserAccount, String> meanCol = new TableColumn<>("Mean");

        table.getColumns().addAll(wordCol, meanCol);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        stage.setTitle("TableView (o7planning.org)");
        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class UserAccount {
    private String word;
    private String mean;

    public UserAccount(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
