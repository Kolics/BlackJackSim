package controllers;

import models.*;

import java.util.List;

public class GameController {

    private Hand hand;
    private long startTime;
    private Card lastCard;
    private int lastTotal;
    private boolean isInitPair;

    public void start() {
        hand = new Hand();
        hand.deal(Card.draw());
        hand.deal(Card.draw());
        startTime = System.currentTimeMillis();
        isInitPair = true;
    }

    public Hand getHand()
    {
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

    public boolean isBlackJack() {
        return isInitPair && hand.isCorrectAnswer(21);
    }

    public void deal() {
        lastTotal = hand.getTotal();
        lastCard = Card.draw();
        List<Card> prevHand = hand.getCards();
        hand = new Hand(prevHand);
        hand.deal(lastCard);
        startTime = System.currentTimeMillis();
        isInitPair = false;
    }

    public int getLastTotal() {
        return lastTotal;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public boolean isInitPair() {
        return isInitPair;
    }
}
