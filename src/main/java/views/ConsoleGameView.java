package views;

import models.*;
import java.util.Scanner;

public class ConsoleGameView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayHand(Hand hand) {
        System.out.println("--- Current hand ---");
        for (Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println("--------------------");
        System.out.print("What is the total? ");
    }

    public int getUserAnswer() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }

    public void showCorrect(long elapsedMs) {
        System.out.println("Correct! Time: " + elapsedMs + "ms");
    }

    public void showWrong(int correctTotal, long elapsedMs) {
        System.out.println("Wrong. Correct answer was: " + correctTotal + " | Time: " + elapsedMs + "ms");
    }

    public void showRoundOver() {
        System.out.println("=== Round over ===\n");
    }
}
