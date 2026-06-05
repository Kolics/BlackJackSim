package models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private List<Integer> possibleAnswers = new ArrayList<>();

    public Hand() {
    }

    public Hand(List<Card> prevHand) {
        cards.addAll(prevHand);
    }

    public void deal(Card card) {
        cards.add(card);
        getTotal();
    }

    public List<Card> getCards() {

        return cards;
    }

    public int getTotal() {
        int total = 0;
        int numOfAces = 0;

        for (Card card : cards) {
            total += card.getValue();
            possibleAnswers.add(total);
            if (card.getNumber() == Card.Number.ACE) {
                numOfAces++;
            }
        }
        while (numOfAces > 0) {
            total = total - 10;
            possibleAnswers.add(total);
            numOfAces--;
        }
        return total;
    }

    public boolean isCorrectAnswer(int userAnswer) {
        return possibleAnswers.contains(userAnswer);
    }

}
