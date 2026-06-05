package models;

import java.util.Random;

public class Card {
    public enum Color   { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Number { TWO, THREE, FOUR, FIVE, SIX, SEVEN,
        EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    private final Color color;
    private final Number number;

    public Card(Color color, Number number) {
        this.color = color;
        this.number = number;
    }

    public static Card draw() {
        Number[] numbers = Number.values();
        Color[] colors = Color.values();
        Random rand = new Random();
        return new Card(
                colors[rand.nextInt(colors.length)],
                numbers[rand.nextInt(numbers.length)]);
    }

    public int getValue() {
        return switch (number) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, JACK, QUEEN, KING -> 10;
            case ACE -> 11;
        };
    }

    public Color getColor() {
        return color;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + " of " + color;
    }
}
