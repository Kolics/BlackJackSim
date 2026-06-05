package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML private TextField answerField;
    @FXML private Button answerButton;
    @FXML private Button startButton;
    @FXML private Label systemOutputLabel;
    @FXML private Label endResultLabel;
    @FXML private HBox cardBox;

    private GameController gameController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameController = new GameController();
    }

    @FXML
    private void startNewRound() {
        gameController.start();
        updateCardDisplay();
        answerField.clear();
    }

    private void updateCardDisplay() {
        cardBox.getChildren().clear();
        for (Card card : gameController.getHand().getCards())
            cardBox.getChildren().add(createNewCard(card));
    }

    private String getColorFromFilename(Card.Color color) {
        return switch (color) {
            case CLUBS    -> "C";
            case SPADES   -> "S";
            case HEARTS   -> "H";
            case DIAMONDS -> "D";
        };
    }

    private int getNumberFromFilename(Card.Number number) {
        return switch (number) {
            case ACE   -> 1;
            case TWO   -> 2;
            case THREE -> 3;
            case FOUR  -> 4;
            case FIVE  -> 5;
            case SIX   -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE  -> 9;
            case TEN   -> 10;
            case JACK  -> 11;
            case QUEEN -> 12;
            case KING  -> 13;
        };
    }

    private StackPane createNewCard(Card card) {
        String filename = getColorFromFilename(card.getColor()) + "-" + getNumberFromFilename(card.getNumber()) + ".png";
        Image image = new Image(getClass().getResourceAsStream("/images/" + filename));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        return new StackPane(imageView);
    }

    @FXML
    private void submit() {
        try {
            int answer = Integer.parseInt(answerField.getText().trim());
            long elapsed = gameController.getTime();
            boolean correct = gameController.isCorrectAnswer(answer);
            systemOutputLabel.setText("");

            if (correct) {
                systemOutputLabel.setText("Correct! " + elapsed + "ms");
            } else {
                systemOutputLabel.setText("Wrong. Answer was: " + gameController.getHand().getTotal());
            }

            if (gameController.isLessThan17()) {
                gameController.deal();
                updateCardDisplay();
                answerField.clear();
            }

        } catch (NumberFormatException e) {
            systemOutputLabel.setText("Please enter a number.");
        }
    }
}