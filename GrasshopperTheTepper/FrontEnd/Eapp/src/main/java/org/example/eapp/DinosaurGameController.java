package org.example.eapp;

import Database.DataConnection;
import Database.Question;
import Model.DinosaurGame.Cactus;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DinosaurGameController implements Initializable {
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private final int boardWidth = 582;
    private final int boardHeight = 250;

    private final int dinoWidth = 88;
    private final int dinoHeight = 94;
    private int dinoX = 10;
    private int dinoY = boardHeight - dinoHeight;

    private final List<Cactus> cactusList = new ArrayList<>();

    private final int cactus1Width = 34;
    private final int cactus2Width = 69;
    private final int cactus3Width = 102;

    private final int cactusHeight = 70;
    private final int cactusX = 700;
    private int cactusY = boardHeight - cactusHeight;

    private double velocityY = 0;
    private double velocityX = -8;
    private final double gravity = 0.4;

    private boolean gameOver = false;
    private int cactusJumps = 0;
    private boolean gamePaused = false;
    private Image dinoImg;
    private Image cactus1Img;
    private Image cactus2Img;
    private Image cactus3Img;
    @FXML
    Label question;
    @FXML
    Label choiceA;
    @FXML
    Label choiceB;
    @FXML
    Button answerA;
    @FXML
    Button answerB;
    @FXML
    Label resultPane;
    @FXML
    Label scorePoints;
    @FXML
    Label clock;
    private Timeline questionTimer;
    private int score = 0;

    DataConnection connection = new DataConnection();
    List<Question> dinosaurQs = connection.getDinosaurDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = canvas.getGraphicsContext2D();
        canvas.setWidth(boardWidth);
        canvas.setHeight(boardHeight);

        try {
            dinoImg = new Image(new FileInputStream("C:\\Users\\HP\\Documents\\code\\tepperapp\\Grasshopper-Li - Copy\\FrontEnd\\Eapp\\src\\main\\resources\\Dinosaur\\dino.png"));
            cactus1Img = new Image(new FileInputStream("C:\\Users\\HP\\Documents\\code\\tepperapp\\Grasshopper-Li - Copy\\FrontEnd\\Eapp\\src\\main\\resources\\Dinosaur\\cactus1.png"));
            cactus2Img = new Image(new FileInputStream("C:\\Users\\HP\\Documents\\code\\tepperapp\\Grasshopper-Li - Copy\\FrontEnd\\Eapp\\src\\main\\resources\\Dinosaur\\cactus2.png"));
            cactus3Img = new Image(new FileInputStream("C:\\Users\\HP\\Documents\\code\\tepperapp\\Grasshopper-Li - Copy\\FrontEnd\\Eapp\\src\\main\\resources\\Dinosaur\\cactus3.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Start game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gamePaused) {
                    update();
                }
            }
        }.start();

        // Place cactus periodically
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> placeCactus()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Handle key events
        canvas.setOnKeyPressed(e -> moveDino(e.getCode()));
        canvas.setFocusTraversable(true);
        canvas.requestFocus();

        setQsBtnVisible(false);
        resultPane.setVisible(false);

        answerA.setText("A");
        answerB.setText("B");
    }

    private void update() {
        scorePoints.setText(score+" / 100");
        if (score >= 100) {
            gameOver = true;
        }
        if (gameOver) {
            scorePoints.setVisible(false);
            resultPane.setVisible(true);
            resultPane.setStyle("-fx-background-color: white; -fx-alignment:center;");
            String congrats = "You have scored " + score + " points!\n";
            if(score>=90) {
                congrats += "Tepper is proud of you";
            } else if(score>=50) {
                congrats+= "Tepper is a little bit disappointed";
            } else {
                congrats+= "You are a disgrace!";
            }
            resultPane.setText(congrats);
            return;
        }
        context.clearRect(0, 0, boardWidth, boardHeight);
        // Draw dinosaur
        velocityY += gravity;
        dinoY = (int) Math.min(dinoY + velocityY, boardHeight - dinoHeight);
        context.drawImage(dinoImg, dinoX, dinoY, dinoWidth, dinoHeight);

        // Draw cacti
        for (Cactus cactus : cactusList) {
            cactus.setX((int) (cactus.getX() + velocityX));
            context.drawImage(cactus.getImg(), cactus.getX(), cactus.getY(), cactus.getWidth(), cactusHeight);

            if (detectCollision(dinoX, dinoY, dinoWidth, dinoHeight, cactus.getX(), cactus.getY(), cactus.getWidth(), cactusHeight)) {
                gameOver = true;
                break;
            }
        }
    }

    private void placeCactus() {
        if (gameOver) {
            return;
        }
        // Place cactus
        Image cactusImg;
        int cactusWidth;
        double placeCactusChance = Math.random();

        if (placeCactusChance > 0.9) { // 10% chance
            cactusImg = cactus3Img;
            cactusWidth = cactus3Width;
        } else if (placeCactusChance > 0.7) { // 30% chance
            cactusImg = cactus2Img;
            cactusWidth = cactus2Width;
        } else { // 60% chance
            cactusImg = cactus1Img;
            cactusWidth = cactus1Width;
        }

        cactusList.add(new Cactus(cactusX, cactusY, cactusWidth, cactusHeight, cactusImg));
        if (cactusList.size() >= 5)
            cactusList.clear();
    }

    private void moveDino(KeyCode keyCode) {
        if (gameOver) {
            return;
        }
        if ((keyCode == KeyCode.SPACE || keyCode == KeyCode.UP) && dinoY == boardHeight - dinoHeight) {
            // Jump
            velocityY = -10;
            cactusJumps++;
        }

        if (cactusJumps % 4 == 0) {
            askQuestion();
        }
        canvas.requestFocus();
    }

    void askQuestion() {
        gamePaused = true;
        clock.setText("10");
        questionTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int currentTime = Integer.parseInt(clock.getText());
            if(currentTime>0) {
                currentTime--;
                clock.setText(String.valueOf(currentTime));
            } else {
                System.out.println("Time's up! You lose!");
                gameOver = true;
                update();
            }
        }));
        questionTimer.setCycleCount(10);
        questionTimer.play();

        int randomIndex = (int) (Math.random() * dinosaurQs.size());
        Question randomQuestion = dinosaurQs.get(randomIndex);

        question.setText(randomQuestion.getQuestion());
        choiceA.setText(randomQuestion.getChoiceA());
        choiceB.setText(randomQuestion.getChoiceB());

        setQsBtnVisible(true);

        answerA.setOnMouseClicked(event -> handleAnswer(randomQuestion,"A"));
        answerB.setOnMouseClicked(event -> handleAnswer(randomQuestion, "B"));
    }

    private void handleAnswer(Question question, String chosenAns) {
        questionTimer.stop();
        gamePaused = false;
        if(question.getAnswer().equals(chosenAns)) {
            gameOver = false;
            System.out.println("Got it right");
            score += 20;
        } else {
            gameOver = true;
            update();
            System.out.println("Dumbass");
        }
        canvas.requestFocus();
        setQsBtnVisible(false);
    }

    private boolean detectCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
    }

    void setQsBtnVisible(boolean check) {
        question.setVisible(check);
        answerA.setVisible(check);
        answerB.setVisible(check);
        choiceA.setVisible(check);
        choiceB.setVisible(check);
    }

}
