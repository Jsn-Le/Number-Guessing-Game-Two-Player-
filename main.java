import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean isRunning = true;

        while (isRunning) {

            // per-round state: bounds, player guesses and secret
            int num1 = 0; // default lower bound
            int num2 = 100; // default upper bound
            int guess1 = 0;
            int guess2 = 0;
            int secretNumber = 0;
            // mode and range selection helpers
            int mode;           // 1 = Fixed, 2 = Endless
            int choice;         // fixed-range choice (1..4)
            int choiceMin;      // computed min for chosen fixed range
            int choiceMax;      // computed max for chosen fixed range
            String choiceRange; // human-readable range for prompts
            // flow-control flags
            boolean fixNumbers; // true => use fixed predefined ranges
            boolean endNumbers; // true when range/guesses for this round are collected

            // Player chooses game modes
            System.out.print("""
                                Modes
                                1. Fixed
                                2. Endless
                                Please select a mode (1/2): 
                                """);
            mode = scanner.nextInt();
            scanner.nextLine(); // consume remaining newline

            // Mode selection: 1 = Fixed (use predefined ranges), 2 = Endless (user-specified bounds)
            if (mode == 1) {
                fixNumbers = true;
                endNumbers = true; // skip asking for bounds (use defaults)
            } else {
                fixNumbers = false;
                endNumbers = false; // will prompt for bounds below
            }

            // Mode 1: Fixed ranges. Present several presets and read player guesses within that preset.
            while (fixNumbers) {
                try {
                    System.out.print("""
                                Ranges
                                1. 1-25
                                2. 1-50
                                3. 1-100
                                4. 1-1000
                                Please select a range (1/2/3/4): 
                                """);
                    choice = scanner.nextInt();

                    // choose preset range and generate secret number
                    switch (choice) {
                        case 1 -> {
                            secretNumber = random.nextInt(25) + 1;
                            choiceRange = "1 to 25";
                            choiceMin = 1;
                            choiceMax = 25;
                        }
                        case 2 -> {
                            secretNumber = random.nextInt(50) + 1;
                            choiceRange = "1 to 50";
                            choiceMin = 1;
                            choiceMax = 50;
                        }
                        case 3 -> {
                            secretNumber = random.nextInt(100) + 1;
                            choiceRange = "1 to 100";
                            choiceMin = 1;
                            choiceMax = 100;
                        }
                        default -> {
                            secretNumber = random.nextInt(1000) + 1;
                            choiceRange = "1 to 1000";
                            choiceMin = 1;
                            choiceMax = 1000;
                        }
                    }

                    // Player One picks a number (validated to be inside chosen range)
                    System.out.print("Player One- Pick a number between " + choiceRange + ": ");
                        do {
                            guess1 = scanner.nextInt();
                            if (guess1 < choiceMin || guess1 > choiceMax) {
                                System.out.println("Please select a number within the range");
                                System.out.print("Try again: ");
                            }
                        } while (guess1 < choiceMin || guess1 > choiceMax);
                    System.out.println("Player One has picked the number: " + guess1);

                    // Player Two picks a number (validated to be inside chosen range)
                    System.out.print("Player Two- Pick a number between " + choiceRange + ": ");
                        do { 
                            guess2 = scanner.nextInt();
                            if (guess2 < choiceMin || guess2 > choiceMax) {
                                System.out.println("Please select a number within the range");
                                System.out.print("Try again: ");
                            }
                        } while (guess2 < choiceMin || guess2 > choiceMax);
                    System.out.println("Player Two has picked the number: " + guess2);

                    // Clears trails from nextInt
                    scanner.nextLine();
                    
                    fixNumbers = false; // all reads succeeded
                } catch (Exception e) {
                    System.out.println("Invalid input - Restarting input for this round.");
                    scanner.nextLine(); // discard bad token/line before retrying
                }
            }

            // Mode 2: Endless. Ask the user for lower/upper bounds, validate them, then read guesses.
            while (!endNumbers) {
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

                    // compute secret number inside the chosen bounds
                    int size = num2 - num1 + 1;
                    secretNumber = random.nextInt(size) + num1;

                    // Player One picks a number (validated to be inside the user-provided bounds)
                    System.out.print("Player One- Pick a number between " + num1 + " and " + num2 + ": ");
                        do {
                            guess1 = scanner.nextInt();
                            if (guess1 < num1 || guess1 > num2) {
                                System.out.println("Please select a number within the range");
                                System.out.print("Try again: ");
                            }
                        } while (guess1 < num1 || guess1 > num2);
                    System.out.println("Player One has picked the number: " + guess1);

                    // Player Two picks a number (validated to be inside the user-provided bounds)
                    System.out.print("Player Two- Pick a number between " + num1 + " and " + num2 + ": ");
                        do {
                            guess2 = scanner.nextInt();
                            if (guess2 < num1 || guess2 > num2) {
                                System.out.println("Please select a number within the range");
                                System.out.print("Try again: ");
                            }
                        } while (guess2 < num1 || guess2 > num2);
                    System.out.println("Player Two has picked the number: " + guess2);

                    // Clears trails from nextInt
                    scanner.nextLine();
                    
                    endNumbers = true; // all reads succeeded
                } catch (Exception e) {
                    System.out.println("Invalid input - Restarting input for this round.");
                    scanner.nextLine(); // discard bad token/line before retrying
                }
            }
            
            // Reveal Stage: pause for dramatic effect and show the secret
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

            // Results: compute distances and decide winner/tie
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

            // Play-again loop: ask user whether to repeat the game
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