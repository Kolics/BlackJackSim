package models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private final List<Integer> possibleAnswers = new ArrayList<>();

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
        int altTotal = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getNumber() == Card.Number.ACE) {
                numOfAces++;
            }
        }
        for (int i = 0; i <= numOfAces; i++) {
            altTotal = total - i * 10;
            addToPossibleAnswers(altTotal);
        }
        while (total > 21 && numOfAces > 0) {
            total = total - 10;
            numOfAces--;
        }
        return total;
    }

    public void addToPossibleAnswers(int A)
    {
        possibleAnswers.add(A);
    }

    public boolean isCorrectAnswer(int userAnswer) {
        return possibleAnswers.contains(userAnswer);
    }

}
