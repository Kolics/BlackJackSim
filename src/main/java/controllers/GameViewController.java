package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import models.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML private TextField answerField;
    @FXML private Button answerButton;
    @FXML private Button startButton;
    @FXML private Label systemOutputLabel;
    @FXML private Label endResultLabel;
    @FXML private HBox cardBox;

    private GameController gameController;
    private ResultManager resultManager;
    private long time;
    private int answer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameController = new GameController();
        resultManager = new ResultManager();
        displayTopResults();
    }

    @FXML
    private void startNewRound() {
        gameController.start();
        updateCardDisplay();
        answerField.clear();
        answerField.setDisable(false);
        if (gameController.isBlackJack()) {
            updateCardDisplay();
            answerField.setDisable(true);
            systemOutputLabel.setText("BlackJack!");
        }
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
            answer = Integer.parseInt(answerField.getText().trim());
            time = gameController.getTime();
            boolean correct = gameController.isCorrectAnswer(answer);
            systemOutputLabel.setText("");

            if (correct) {
                resultManager.save(getResult());
                displayTopResults();
                systemOutputLabel.setText("Correct! " + time + "ms");
            } else {
                systemOutputLabel.setText("Wrong. Answer was: " + gameController.getHand().getTotal());
            }

            if (gameController.isLessThan17()) {
                gameController.deal();
                updateCardDisplay();
                answerField.clear();
            }
            else {
                gameController.deal();
                updateCardDisplay();
                answerField.setDisable(true);
            }

        } catch (NumberFormatException e) {
            systemOutputLabel.setText("Please enter a number.");
        }
    }

    private Result getResult() {
        String resultString;
        int lastTotal = gameController.getLastTotal();
        long lastTime = time;

        if (gameController.isInitPair()) {
            Card card1 = gameController.getHand().getCards().get(0);
            Card card2 = gameController.getHand().getCards().get(1);
            resultString = card1.getValue() + " + " + card2.getValue();
        } else {
            resultString = lastTotal + " + " + gameController.getLastCard().getValue();
        }

        Result result = new Result(resultString, answer , lastTime);

        return result;
    }

    private void displayTopResults() {
        List<Result> topResults = resultManager.getTopResults(10);
        StringBuilder highScoreText = new StringBuilder();
        int pos = 1;

        for (Result result : topResults) {
            highScoreText.append(pos).append(". ").append(result.toString()).append("\n");
            pos++;
        }

        endResultLabel.setText(highScoreText.toString());
    }
}