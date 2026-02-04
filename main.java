import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean isRunning = true;

        while (isRunning) {

            int num1;
            int num2;
            int guess1 = 0;
            int guess2 = 0;
            int secretNumber = 0;

            boolean gotNumbers = false;
            while (!gotNumbers) {
                try {
                    // Players choose a range to guess from
                    System.out.println("Enter the lower bound (>=0): ");
                        do { 
                            num1 = scanner.nextInt();
                            if (num1 < 0) {
                                System.out.println("Please enter a number equal or greater to 0");
                            } 
                        } while (num1 < 0);

                    System.out.println("Enter the upper bound (must be > lower bound): ");
                        do { 
                            num2 = scanner.nextInt();
                            if (num2 <= num1) {
                                System.out.println("Please enter a number equal or greater to lower bound");
                            } 
                        } while (num2 <= num1);

                    System.out.println("The guessing range for this game is " + num1 + " and " + num2);

                    int size = num2 - num1 + 1;
                    secretNumber = random.nextInt(size) + num1;

                    // Player One picks a number
                    System.out.print("Player One- Pick a number between " + num1 + " and " + num2 + ": ");
                        guess1 = scanner.nextInt();
                    System.out.println("Player One has picked the number: " + guess1);

                    // Player Two picks a number
                    System.out.print("Player Two- Pick a number between " + num1 + " and " + num2 + ": ");
                        guess2 = scanner.nextInt();
                    System.out.println("Player Two has picked the number: " + guess2);

                    // Clears trails from nextInt
                    scanner.nextLine();
                    
                    gotNumbers = true; // all reads succeeded
                } catch (Exception e) {
                    System.out.println("Invalid input - please enter integer only. Restarting input for this round.");
                    scanner.nextLine(); // discard bad token/line before retrying
                }
            }
            
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

            // Loop
            String resp;

            do {
                System.out.print("Play again? (y/n): ");
                    resp = scanner.nextLine().trim().toLowerCase();
                if (!resp.equals("y") && !resp.equals("n")) {
                    System.out.println("Please enter 'y' or 'n'");
                }
            } while (!resp.equals("y") && !resp.equals("n"));
            isRunning = resp.equals("y");
        }

        scanner.close();
    }
}