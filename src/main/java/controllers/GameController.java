package controllers;

import models.*;

public class GameController {

    private Hand hand;
    private long startTime;

    public void start() {
        hand = new Hand();
        hand.deal(Card.draw());
        hand.deal(Card.draw());
        startTime = System.currentTimeMillis();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isCorrectAnswer(int userAnswer) {
        return hand.isCorrectAnswer(userAnswer);
    }

    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean isLessThan17() {
        return hand.getTotal() < 17;
    }

    public void deal() {
        hand.deal(Card.draw());
        startTime = System.currentTimeMillis();
    }
}
