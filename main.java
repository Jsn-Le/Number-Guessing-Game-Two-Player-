import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1;

        // Player One picks a number
        System.out.print("Player One- Pick a number between 1-100: ");
            int guess1 = scanner.nextInt();
        System.out.println("Player One has picked the number: " + guess1);

        // Player Two picks a number
        System.out.print("Player Two- Pick a number between 1-100: ");
            int guess2 = scanner.nextInt();
        System.out.println("Player Two has picked the number: " + guess2);

        // Reveal Number
        System.out.println("Both players have picked a number!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("The number is...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(secretNumber + "!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Results -- who won
        int d1 = Math.abs(secretNumber - guess1);
        int d2 = Math.abs(secretNumber - guess2);

        if (guess1 == secretNumber && guess2 == secretNumber) {
            System.out.println("A tie! Both players picked the correct number.");
        } else if (guess1 == secretNumber) {
            System.out.println("Player One wins with a correct guess!");
        } else if (guess2 == secretNumber) {
            System.out.println("Player Two wins with a correct guess!");
        } else if (d1 < d2) {
            System.out.println("Player One wins with a closer guess!");
        } else if (d1 > d2) {
            System.out.println("Player Two wins with a closer guess!");
        } else {
            System.out.println("A tie! Both players were equally close to the secret number.");
        }

        scanner.close();
    }
}